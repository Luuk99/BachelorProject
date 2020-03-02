package e.www.cameratest

import android.content.Context
import android.graphics.ImageFormat
import android.hardware.camera2.*
import android.hardware.camera2.params.StreamConfigurationMap
import android.media.ImageReader
import android.os.Bundle
import android.util.Size
import android.view.Surface
import androidx.appcompat.app.AppCompatActivity


// Variables for camera device and session
private lateinit var imageReader: ImageReader
private var cameraId: String? = null
private lateinit var cameraDevice: CameraDevice

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create an image reader for the capture session
        val imageReader = ImageReader.newInstance(ImageFormat.PRIVATE, 1, 1, 1)
        val imReaderSurface = imageReader.surface
        val targets: MutableList<Surface> = arrayOf(imReaderSurface).toMutableList()

        // Set up the camera
        setupCamera()

        // Create the camera capture session
        cameraDevice.createCaptureSession(targets, object: CameraCaptureSession.StateCallback() {
            override fun onConfigured(session: CameraCaptureSession) {
                // Do something with `session`
            }
            override fun onConfigureFailed(session: CameraCaptureSession) = Unit
        }, null)
    }

    // Sets up the camera
    private fun setupCamera() {
        val manager = getSystemService(Context.CAMERA_SERVICE) as CameraManager

        try {
            for (thisCameraId in manager.cameraIdList) {
                val characteristics =
                    manager.getCameraCharacteristics(thisCameraId)
                if (characteristics.get(CameraCharacteristics.LENS_FACING) != CameraCharacteristics.LENS_FACING_FRONT) {
                    continue
                }
                cameraId = thisCameraId
                val streamConfigurationMap: StreamConfigurationMap =
                    characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)!!
                val sizes: Array<Size> =
                    streamConfigurationMap.getOutputSizes(ImageFormat.RAW_SENSOR)
                val picWidth = sizes[0].width
                val picHeight = sizes[0].height
                imageReader =
                    ImageReader.newInstance(picWidth, picHeight, ImageFormat.PRIVATE, 1)
                imageReader.setOnImageAvailableListener(onImageAvailableListener, backgroundHandler)
            }
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }
}
