
public class FrameFactory {

	// This pattern takes out the responsibility of the instantiation of a class from the client program to the factory class.
	public static FrameStructure getFrame(String type, User user){
		
		if("ACCESS".equalsIgnoreCase(type)) return new Access();
		else if("REG".equalsIgnoreCase(type)) return new RegForm();
		else if("MENU".equalsIgnoreCase(type)) return new Menu(user);
		else if("LOGIN".equalsIgnoreCase(type)) return new LoginFrame();
		
		return null;
	}

}
