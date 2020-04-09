import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class GenerateKey {
    private KeyPairGenerator keyGen;
    private KeyPair pair;
    private PrivateKey privateKey;
    private PublicKey publicKey;


    public static void main(String[] args)throws Exception{

       GenerateKey g=new GenerateKey();
       g.saveKeys();
    }

    public  void saveKeys()throws Exception{
        createKeys(1120);
        writeTofile("MyKeys/publicKey", getPublicKey().getEncoded());
        writeTofile("MyKeys/privateKey", getPrivateKey().getEncoded());
    }


    public void createKeys(int keyLength) throws  Exception{
        this.keyGen=KeyPairGenerator.getInstance("RSA");
        this.keyGen.initialize(keyLength);
        this.pair=this.keyGen.generateKeyPair();
        this.privateKey=this.pair.getPrivate();
        this.publicKey=this.pair.getPublic();
    }

    public PrivateKey getPrivateKey(){
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void writeTofile(String path, byte[] key)throws IOException {
        File f = new File(path);
        f.getParentFile().mkdirs();

        FileOutputStream fos = new FileOutputStream(f);
        fos.write(key);
        fos.flush();
        fos.close();
    }



}
