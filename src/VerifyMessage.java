import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;

public class VerifyMessage {


    public static void main(String[] args)throws Exception{
        VerifyMessage verifyMessage=new VerifyMessage();

        //check correct public Key
        System.out.println(verifyMessage.verify(verifyMessage.getPublicKey("MyKeys/publicKey")));

        //check Wrong public Key
//        System.out.println(verifyMessage.verify(verifyMessage.getWrongPublicKey()));
    }

    public PublicKey getPublicKey(String filename) throws Exception {
        byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    public  boolean verify(PublicKey publicKey) throws Exception{
        Signature signature=Signature.getInstance("SHA1withRSA");
        signature.initVerify(publicKey);
        byte[] messageBytes = Files.readAllBytes(Paths.get("E:/RIB.txt"));
        signature.update(messageBytes);
        return  signature.verify(Files.readAllBytes(Paths.get("digital_signature")));
    }

    public PublicKey getWrongPublicKey() throws Exception{
        KeyPairGenerator kp=KeyPairGenerator.getInstance("RSA");
        kp.initialize(1120);
        KeyPair pair=kp.generateKeyPair();

        return  pair.getPublic();
    }


}
