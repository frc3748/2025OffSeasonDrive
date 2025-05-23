package frc.robot.subsystems.drivetrain;
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.pathplanner.lib.config.ModuleConfig;
import com.pathplanner.lib.config.RobotConfig;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;

public class DriveConstants {
  public static final double maxSpeedMetersPerSec = 5;
  public static final double odometryFrequency = 100.0; // Hz
  public static final double trackWidth = Units.inchesToMeters(24.5);
  public static final double wheelBase = Units.inchesToMeters(24.5);
  public static final double driveBaseRadius = Math.hypot(trackWidth / 2.0, wheelBase / 2.0);
  public static final Translation2d[] moduleTranslations =
      new Translation2d[] {
        new Translation2d(trackWidth / 2.0, wheelBase / 2.0),
        new Translation2d(trackWidth / 2.0, -wheelBase / 2.0),
        new Translation2d(-trackWidth / 2.0, wheelBase / 2.0),
        new Translation2d(-trackWidth / 2.0, -wheelBase / 2.0)
      };


     
  // angle off sets in radians 
  public static final Rotation2d frontLeftZeroRotation = new Rotation2d(1.31155210102);

  public static final Rotation2d frontRightZeroRotation = new Rotation2d(-2.86547807527);
  public static final Rotation2d backLeftZeroRotation = new Rotation2d(2.01871832416);
  public static final Rotation2d backRightZeroRotation = new Rotation2d(-2.05399840966);

  // Device CAN IDs
  public static final int pigeonCanId = 50;

  public static final int frontLeftDriveCanId = 8;
  public static final int backLeftDriveCanId = 6;
  public static final int frontRightDriveCanId = 2;
  public static final int backRightDriveCanId = 4;

  public static final int frontLeftTurnCanId = 9;
  public static final int backLeftTurnCanId = 7;
  public static final int frontRightTurnCanId = 3;
  public static final int backRightTurnCanId = 5;


  public static final int frontLeftTurnCanCoderId = 4;
  public static final int backLeftTurnCanCoderId = 3;
  public static final int frontRightTurnCanCoderId = 1;
  public static final int backRightTurnCanCoderId = 2;


  // Drive motor configuration
  public static final int driveMotorCurrentLimit = 50;
  public static final double wheelRadiusMeters = Units.inchesToMeters(2);
  public static final double driveMotorReduction = 6.48;
  public static final DCMotor driveGearbox = DCMotor.getNeoVortex(1);

  // Drive encoder configuration
  public static final double driveEncoderPositionFactor =
      2 * Math.PI / driveMotorReduction; // Rotor Rotations -> Wheel Radians
  public static final double driveEncoderVelocityFactor =
      (2 * Math.PI) / 60.0 / driveMotorReduction; // Rotor RPM -> Wheel Rad/Sec

  // Drive PID configuration
  public static final double driveKp = 0.008;
  public static final double driveKd = 0.0;
  public static final double driveKs = 0.0;
  public static final double driveKv = 0.1;
  public static final double driveSimP = 0.05;
  public static final double driveSimD = 0.0;
  public static final double driveSimKs = 0.0;
  public static final double driveSimKv = 0.0789;

  // Turn motor configuration
  public static final boolean turnInverted = false;
  public static final int turnMotorCurrentLimit = 50;
  public static final double turnMotorReduction = 12.1;
  public static final DCMotor turnGearbox = DCMotor.getNEO(1);

  // Turn encoder configuration
  public static final boolean turnEncoderInverted = true;
  public static final double turnEncoderPositionFactor = 2 * Math.PI / turnMotorReduction; // Rotations -> Radians
  public static final double turnEncoderVelocityFactor = (2 * Math.PI) / 60.0 / turnMotorReduction; // RPM -> Rad/Sec
  public static CANcoderConfiguration canCodeConfiguration = new CANcoderConfiguration();

  // Turn PID configuration
  public static final double turnKp = 0.007;
  public static final double turnKd = 0.0;
  public static final double turnSimP = 8.0;
  public static final double turnSimD = 0.0;
  public static final double turnPIDMinInput = 0; // Radians
  public static final double turnPIDMaxInput = 2 * Math.PI; // Radians

  // PathPlanner configuration
  public static final double robotMassKg = 49.34;
  public static final double robotMOI = 6.883;
  public static final double wheelCOF = 1.2;
  public static final RobotConfig ppConfig =
      new RobotConfig(
          robotMassKg,
          robotMOI,
          new ModuleConfig(
              wheelRadiusMeters,
              maxSpeedMetersPerSec,
              wheelCOF,
              driveGearbox.withReduction(driveMotorReduction),
              driveMotorCurrentLimit,
              1),
          moduleTranslations);
}