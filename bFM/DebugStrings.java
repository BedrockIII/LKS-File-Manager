package bFM;

public abstract class DebugStrings 
{
	public final static String ReadFileAttempt = "Attempting to read %s File at: \"%s\""; // Needs File Type and Path
	public final static String WriteFileAttempt = "Attempting to write %s File at: \"%s\""; // Needs File Type and Path
	public final static String ReadFileFailureNewMode = "Failed to read %s File. Program will attempt to %s from a %s File at \"%s.\""; // Needs File Type and mode and file type and Path
	public final static String ReadFileFailureCreateNew = "Could not find preexisting %s file at \"%s\". Creating new empty %s File"; // Needs File Type and Path
	public final static String ReadFileFailureEnd = "Failed to read %s File. Program will attempt to %s from a %s File at \"%s.\""; // Needs File Type
	public final static String ReadFileFailureNoMemory = "Failed to read %s File and it was not."; // Needs File Type
	public final static String WriteFileFailureEnd = "Failed to write %s File."; // Needs File Type
	public final static String ReadFileSuccess = "Sucessfully read %s File"; // Needs File Type
	public final static String WriteFileSuccess = "Sucessfully wrote %s File"; // Needs File Type
	public final static String RequiredFileNotInPackageEnd = "The %s File is required so the program will stop."; // Needs File Type
}
