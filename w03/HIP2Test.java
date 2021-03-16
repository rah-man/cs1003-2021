import java.io.*;

public class HIP2Test {
  public static void main(String [] argv) {
    try {
      HIP2Reader h = new HIP2Reader( argv[0] );
      while (h.hasNextStar()) {
        HIP2Reader.HIP2Star s = h.getNextStar();
        System.out.println("ID "+s.StarID+" Coords "+s.RA+":"+s.Decl+" Parallax "+ s.parallax + " milliarcsecs  magnitude "+ s.magnitude);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
