package bFM;

public abstract class DebugStrings 
{
	public final static String ReadFileAttempt = "Attempting to read %s File at: \"%s\""; // Needs File Type and Path
	public final static String WriteFileAttempt = "Attempting to write %s File at: \"%s\""; // Needs File Type and Path
	public final static String ReadFileFailureContinue = "Failed to read %s File. Program will attempt to %s from a %s File at \"%s.\""; // Needs File Type and mode and file type and Path
	public final static String ReadFileFailureEnd = "Failed to read %s File."; // Needs File Type
	public final static String WriteFileFailureEnd = "Failed to write %s File."; // Needs File Type
	public final static String ReadFileSuccess = "Sucessfully read %s File"; // Needs File Type
	public final static String WriteFileSuccess = "Sucessfully wrote %s File"; // Needs File Type
}
