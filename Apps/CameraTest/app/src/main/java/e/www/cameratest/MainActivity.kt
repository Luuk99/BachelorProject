package e.www.cameratest

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.ImageFormat
import android.hardware.camera2.*
import android.hardware.camera2.CameraCaptureSession.CaptureCallback
import android.hardware.camera2.params.StreamConfigurationMap
import android.media.ImageReader
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.util.Size
import android.view.Surface
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlin.properties.Delegates

// Constants for the frequencies
const val low_frequency: Long = 15000 // 15 seconds
const val medium_frequency: Long = 10000 // 10 seconds
const val high_frequency: Long = 5000 // 5 seconds

// Runnable variable for repeating the action
private var runnable: Runnable by Delegates.notNull()

// Handler for infinite loop
private lateinit var handler: Handler

// Context variable
private lateinit var mContext: Context

// Variables for camera device and session
private lateinit var imageReader: ImageReader
private var cameraId: String? = null
private var cameraDevice: CameraDevice? = null
private var mSession: CameraCaptureSession? = null
private var cameraManager: CameraManager? = null
private var cameraCharacteristics: CameraCharacteristics? = null
private var biggestSize: Size? = null
private var targets: MutableList<Surface>? = null

// Callback to assign cameraDevice
private val mStateCallback: CameraDevice.StateCallback = object : CameraDevice.StateCallback() {
    override fun onOpened(camera: CameraDevice) {
        cameraDevice = camera
        cameraDevice?.createCaptureSession(targets!!, mccsStateCallback, null)
    }

    override fun onDisconnected(camera: CameraDevice) {
        cameraDevice?.close()
    }

    override fun onError(camera: CameraDevice, error: Int) {
        cameraDevice?.close()
        cameraDevice = null
    }
}

private val mccsStateCallback: CameraCaptureSession.StateCallback =
    object : CameraCaptureSession.StateCallback() {
        override fun onConfigured(session: CameraCaptureSession) {
            try {
                if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(
                        mContext.applicationContext,
                        Manifest.permission.CAMERA
                    )
                ) {
                    mSession = session
                    val request =
                        cameraDevice!!.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
                    request.addTarget(targets!![0])

                    // Handler for infinite loop
                    handler = Handler()

                    // Runnable which takes a picture
                    runnable = Runnable {
                        // Take a picture
                        mSession?.capture(request.build(), object : CaptureCallback() {
                            override fun onCaptureCompleted(
                                session: CameraCaptureSession,
                                request: CaptureRequest,
                                result: TotalCaptureResult
                            ) {
                                super.onCaptureCompleted(session, request, result)
                            }
                        }, null)

                        handler.postDelayed(runnable, high_frequency)
                    }

                    // Start infinite loop
                    handler.post(runnable)
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        override fun onConfigureFailed(session: CameraCaptureSession) {}
    }

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mContext = this

        // Set up the camera
        initialiseCamera()
    }

    private fun initialiseCamera() {
        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {
            cameraId = getFrontFacingCameraId(cameraManager)
            if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                )
            ) {
                cameraCharacteristics = cameraManager?.getCameraCharacteristics(cameraId!!)
                val streamConfigurationMap: StreamConfigurationMap? =
                    cameraCharacteristics?.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)
                val jpegSizes =
                    streamConfigurationMap!!.getOutputSizes(ImageFormat.JPEG)
                biggestSize = Size(0, 0)
                for (size in jpegSizes) {
                    if (size.height >= biggestSize!!.height && size.width >= biggestSize!!.width) {
                        biggestSize = size
                    }
                }
                imageReader =
                    ImageReader.newInstance(biggestSize!!.width, biggestSize!!.height, ImageFormat.PRIVATE, 50)
                val imReaderSurface = imageReader.surface
                targets = arrayOf(imReaderSurface).toMutableList()
                cameraManager?.openCamera(cameraId!!, mStateCallback, null)
            } else {
                Log.d("Camera permission", "Missing")
            }
        } catch (ex: java.lang.Exception) {
            ex.printStackTrace()
        }
    }

    private fun getFrontFacingCameraId(cameraManager: CameraManager?): String? {
        try {
            for (id in cameraManager!!.cameraIdList) {
                val cameraCharacteristics =
                    cameraManager.getCameraCharacteristics(id)
                val cameraOrientation =
                    cameraCharacteristics.get(CameraCharacteristics.LENS_FACING)
                if (cameraOrientation != null && cameraOrientation == CameraMetadata.LENS_FACING_FRONT) {
                    return id
                }
            }
        } catch (ex: CameraAccessException) {
            ex.printStackTrace()
        }
        return null
    }

    override fun onPause() {
        // Stop the loop
        handler.removeCallbacks(runnable)

        super.onPause()
    }
}
