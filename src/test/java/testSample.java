import java.io.IOException;
import java.util.ArrayList;

public class testSample {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		dataDriven d = new dataDriven();
		ArrayList<String> returnedData = d.getData("Add Profile");
		System.out.println(returnedData.get(0));
		System.out.println(returnedData.get(1));
		System.out.println(returnedData.get(2));
		System.out.println(returnedData.get(3));
	}

}
