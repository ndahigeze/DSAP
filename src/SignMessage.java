
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

public class SignMessage {


    public static void main(String[] args) throws Exception{
        SignMessage s=new SignMessage();
        s.sign();
    }

    public void sign() throws Exception{
        Signature sign=Signature.getInstance("SHA1withRSA");
        sign.initSign(getPrivate("MyKeys/privateKey"));

        byte[] bytes=Files.readAllBytes(Paths.get("E:/RIB.txt"));
        sign.update(bytes);
        byte[] signature = sign.sign();
        Files.write(Paths.get("digital_signature"),signature);
    }

    public PrivateKey getPrivate(String filename) throws Exception {
        byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

}
