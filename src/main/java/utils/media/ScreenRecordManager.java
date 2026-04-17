package utils.media;

import com.automation.remarks.video.RecorderFactory;
import com.automation.remarks.video.recorder.IVideoRecorder;
import com.automation.remarks.video.recorder.VideoRecorder;
import utils.dataReader.PropertyReader;
import utils.logs.LogsManager;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;

import java.io.File;

public class ScreenRecordManager {

    public static final String RECORDINGS_PATH = "test-outputs/recordings/";
    private static final ThreadLocal<IVideoRecorder> recorder = new ThreadLocal<>();

    public static void startRecording() {
        if ("true".equalsIgnoreCase(PropertyReader.getProperty("recordTests"))) {
            try {
                File recordingsDir = new File(RECORDINGS_PATH);
                if (!recordingsDir.exists()) {
                    recordingsDir.mkdirs();
                }

                if ("local".equalsIgnoreCase(PropertyReader.getProperty("executionType"))) {
                    recorder.set(RecorderFactory.getRecorder(VideoRecorder.conf().recorderType()));

                    if (recorder.get() != null) {
                        recorder.get().start();
                        LogsManager.info("Recording started");
                    }
                }
            } catch (Exception e) {
                LogsManager.error("Failed to start recording: " + e.getMessage());
            }
        }
    }

    public static void stopRecording(String testMethodName) {
        try {
            if (recorder.get() != null) {
                String videoFilePath = String.valueOf(recorder.get().stopAndSave(testMethodName));
                File videoFile = new File(videoFilePath);

                LogsManager.info("Video file saved at: " + videoFile.getAbsolutePath());

                File mp4File = encodeRecording(videoFile);
                LogsManager.info("Recording stopped and converted to MP4: " + mp4File.getName());
            }
        } catch (Exception e) {
            LogsManager.error("Failed to stop recording: " + e.getMessage());
        } finally {
            recorder.remove();
        }
    }

    private static File encodeRecording(File sourceFile) {
        File targetFile = new File(
                sourceFile.getParent(),
                sourceFile.getName().replaceAll("\\.[^.]+$", "") + ".mp4"
        );

        try {
            MultimediaObject multimediaObject = new MultimediaObject(sourceFile);

            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("aac");

            VideoAttributes video = new VideoAttributes();
            video.setCodec("libx264");
            video.setBitRate(800000);
            video.setFrameRate(30);

            EncodingAttributes encodingAttributes = new EncodingAttributes();
            encodingAttributes.setOutputFormat("mp4");
            encodingAttributes.setAudioAttributes(audio);
            encodingAttributes.setVideoAttributes(video);

            Encoder encoder = new Encoder();
            encoder.encode(multimediaObject, targetFile, encodingAttributes);

            if (targetFile.exists()) {
                if (sourceFile.delete()) {
                    LogsManager.info("Deleted original video file: " + sourceFile.getName());
                } else {
                    LogsManager.warn("Could not delete original video file: " + sourceFile.getName());
                }
            }

        } catch (EncoderException e) {
            LogsManager.error("Failed to convert video to MP4: " + e.getMessage());
        } catch (Exception e) {
            LogsManager.error("Unexpected error while encoding recording: " + e.getMessage());
        }

        return targetFile;
    }
}