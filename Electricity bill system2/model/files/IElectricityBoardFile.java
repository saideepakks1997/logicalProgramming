package files;

import eb.ElectricityBoard;

public interface IElectricityBoardFile {
	public ElectricityBoard getElectricityBoardObj();
	public void createSeriesNoFile(ElectricityBoard eb);
	public void updateSeriesNo(String field);
}
