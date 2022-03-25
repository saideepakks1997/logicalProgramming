package input_output_streams;



import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

public class MyObjectInputStream extends ObjectInputStream{

	public MyObjectInputStream(InputStream in) throws IOException {
		super(in);
	}

	protected MyObjectInputStream() throws IOException, SecurityException {
		super();
	}
	
	@Override
	protected void readStreamHeader() throws IOException, StreamCorruptedException {
		return;
	}

}
