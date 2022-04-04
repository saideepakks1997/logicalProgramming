package factory;

import connection.Connection;
import connection.CottageAndTinyIndustries;
import connection.Domestic;
import connection.GovnAidedPlaces;
import connection.LtCommercial;
import connection.PowerLooms;
import connection.PrivateHostpitalInstitution;
import connection.PublicLightTown;
import connection.PublicLightsVillageAndIndustrialmetro;
import connection.PublicWorkshop;
import connection.TemporarySupply;
import connection.TypeOfConnection;
import consumer.Consumer;
import eb.Tarrifs;

public class ConnectionFactoryObj implements IConnectionFactory{

	@Override
	public Connection getConnectionObj(long serviceNo, TypeOfConnection connType, String connAddress,
			Consumer consumer) {
		Connection con = null;
		if(connType == TypeOfConnection.Domestic)
			con = new Domestic(serviceNo,connType ,connAddress, consumer);
		
		else if(connType == TypeOfConnection.LtCommercial)
			con = new LtCommercial(serviceNo,connType ,connAddress, consumer);
		
		else if(connType == TypeOfConnection.PublicWorkshop)
			con = new PublicWorkshop(serviceNo,connType ,connAddress, consumer);
		
		else if(connType == TypeOfConnection.CottageAndTinyIndustries)
			con = new CottageAndTinyIndustries(serviceNo,connType ,connAddress, consumer);
		
		else if(connType == TypeOfConnection.PowerLooms)
			con = new PowerLooms(serviceNo,connType ,connAddress, consumer);
		
		else if(connType == TypeOfConnection.PublicLightsVillageAndIndustrialmetro)
			con = new PublicLightsVillageAndIndustrialmetro(serviceNo,connType ,connAddress, consumer);
		
		else if(connType == TypeOfConnection.TemporarySupply)
			con = new TemporarySupply(serviceNo,connType ,connAddress, consumer);
		
		else if(connType == TypeOfConnection.PublicLightTown)
			con = new PublicLightTown(serviceNo,connType ,connAddress, consumer);
		
		else if(connType == TypeOfConnection.GovnAidedPlaces)
			con = new GovnAidedPlaces(serviceNo,connType ,connAddress, consumer);
		
		else if(connType == TypeOfConnection.PrivateHostpitalInstitution)
			con = new PrivateHostpitalInstitution(serviceNo,connType ,connAddress, consumer);
		return con;
	}
	
}
