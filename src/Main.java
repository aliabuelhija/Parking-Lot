class Singleton {
	
	
    // Static variable reference of single_instance
    // of type Singleton
    private static Singleton single_instance = null;
 
    // Declaring a variable of type String
    public FrameStructure s;
 
    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private Singleton(FrameStructure a)
    {
        s = a;
    }
 
    // Static method
    // Static method to create instance of Singleton class
    public static Singleton getInstance()
    {
        if (single_instance == null)
            single_instance = new Singleton(FrameFactory.getFrame("ACCESS", null));
 
        return single_instance;
    }
}

public class Main {
	public static void main(String[] args) {
		Singleton.getInstance();
	}

}
