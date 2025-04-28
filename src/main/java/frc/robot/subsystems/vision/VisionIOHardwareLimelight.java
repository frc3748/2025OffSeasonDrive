package frc.robot.subsystems.vision;

import java.util.concurrent.atomic.AtomicReference;

import frc.robot.util.LimelightHelpers;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class VisionIOHardwareLimelight implements VisionIO {
    NetworkTable table = NetworkTableInstance.getDefault().getTable(VisionConstants.kLimelightTableName);
    NetworkTable tableB = NetworkTableInstance.getDefault().getTable(VisionConstants.kLimelightBTableName);

    AtomicReference<VisionIOInputs> latestInputs = new AtomicReference<>(new VisionIOInputs());

    public VisionIOHardwareLimelight() {
        setLLSettings();
    }

    private void setLLSettings() {
        double[] camerapose = { 0.0, 0.0, VisionConstants.kCameraHeightOffGroundMeters, 0.0, VisionConstants.kCameraPitchDegrees,
                0.0 };
        table.getEntry("camerapose_robotspace_set").setDoubleArray(camerapose);

        double[] cameraBpose = { 0.0, 0.0, VisionConstants.kCameraBHeightOffGroundMeters, VisionConstants.kCameraBRollDegrees,
            VisionConstants.kCameraBPitchDegrees, 0.0 };
        tableB.getEntry("camerapose_robotspace_set").setDoubleArray(cameraBpose);

    }

    @Override
    public void readInputs(VisionIOInputs inputs) {
        inputs.turretCameraSeesTarget = table.getEntry("tv").getDouble(0) == 1.0;
        inputs.elevatorCameraSeesTarget = tableB.getEntry("tv").getDouble(0) == 1.0;
        if (inputs.turretCameraSeesTarget) {
            // Read megatag updates
            var megatag = LimelightHelpers.getBotPoseEstimate_wpiBlue(VisionConstants.kLimelightTableName);
            var megatag2 = LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2(VisionConstants.kLimelightTableName);
            inputs.turretCameraMegatagPoseEstimate = MegatagPoseEstimate.fromLimelight(megatag);
            inputs.turretCameraMegatagCount = megatag.tagCount;
            inputs.turretCameraMegatag2PoseEstimate = MegatagPoseEstimate.fromLimelight(megatag2);
            inputs.turretCameraFiducialObservations = FiducialObservation.fromLimelight(megatag.rawFiducials);
        }
        if (inputs.elevatorCameraSeesTarget) {
            // Read megatag updates
            var megatag = LimelightHelpers.getBotPoseEstimate_wpiBlue(VisionConstants.kLimelightBTableName);
            var megatag2 = LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2(VisionConstants.kLimelightBTableName);
            inputs.elevatorCameraMegatagPoseEstimate = MegatagPoseEstimate.fromLimelight(megatag);
            inputs.elevatorCameraMegatagCount = megatag.tagCount;
            inputs.elevatorCameraMegatag2PoseEstimate = MegatagPoseEstimate.fromLimelight(megatag2);
            inputs.elevatorCameraFiducialObservations = FiducialObservation.fromLimelight(megatag.rawFiducials);
        }
        latestInputs.set(inputs);

        // Set the persistent settings into NT
        setLLSettings();
    }

    @Override
    public void pollNetworkTables() {
        VisionIOInputs inputs = new VisionIOInputs();

        // See if we see the target
        inputs.turretCameraSeesTarget = table.getEntry("tv").getDouble(0) == 1.0;
        inputs.elevatorCameraSeesTarget = tableB.getEntry("tv").getDouble(0) == 1.0;
        if (inputs.turretCameraSeesTarget) {
            // Read megatag updates
            var megatag = LimelightHelpers.getBotPoseEstimate_wpiBlue(VisionConstants.kLimelightTableName);
            var megatag2 = LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2(VisionConstants.kLimelightTableName);
            inputs.turretCameraMegatagPoseEstimate = MegatagPoseEstimate.fromLimelight(megatag);
            inputs.turretCameraMegatagCount = megatag.tagCount;
            inputs.turretCameraMegatag2PoseEstimate = MegatagPoseEstimate.fromLimelight(megatag2);
            inputs.turretCameraFiducialObservations = FiducialObservation.fromLimelight(megatag.rawFiducials);
        }
        if (inputs.elevatorCameraSeesTarget) {
            // Read megatag updates
            var megatag = LimelightHelpers.getBotPoseEstimate_wpiBlue(VisionConstants.kLimelightBTableName);
            var megatag2 = LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2(VisionConstants.kLimelightBTableName);
            inputs.elevatorCameraMegatagPoseEstimate = MegatagPoseEstimate.fromLimelight(megatag);
            inputs.elevatorCameraMegatagCount = megatag.tagCount;
            inputs.elevatorCameraMegatag2PoseEstimate = MegatagPoseEstimate.fromLimelight(megatag2);
            inputs.elevatorCameraFiducialObservations = FiducialObservation.fromLimelight(megatag.rawFiducials);
        }
        latestInputs.set(inputs);

        // Set the persistent settings into NT
        setLLSettings();
    }
}