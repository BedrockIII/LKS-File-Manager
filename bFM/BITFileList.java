package bFM;

import javax.swing.JMenuItem;

import GUI.FileList.CollapseableFileList;

public interface BITFileList
{
	abstract void addExportBITAction();
	abstract void addImportBITAction();
	abstract void addReplaceBITAction();
	public default JMenuItem createExportBITAction(IntermediateFileFormat object)
	{
		return GUIUtils.createExportAction("Export as .bit text file", object.getBITName(), "Bedrock's Intermediate Text File", object::toBIT);
	}
	public default JMenuItem createImportBITAction(IntermediateFileFormat object, CollapseableFileList source)
	{
		return GUIUtils.createImportAction("Import from .bit text file", "Bedrock's Intermediate Text File", "bit", object::importFromBIT, source);
	}
	public default JMenuItem createReplaceBITAction(IntermediateFileFormat object, CollapseableFileList source)
	{
		return GUIUtils.createImportAction("Replace from .bit text file", "Bedrock's Intermediate Text File", "bit", object::replaceFromBIT, source);
	}
}
