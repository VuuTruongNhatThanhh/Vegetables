package vn.edu.hcmuaf.fit.Dao;

import vn.edu.hcmuaf.fit.database.DBConnect;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
public class KeyDao {
    public static KeyDao instance;

    public KeyDao() {
    }

    public static KeyDao getInstance() {
        if (instance == null) {
            instance = new KeyDao();
        }
        return instance;
    }

    public void addDB(String id_user, String publickey, int status) {
        PreparedStatement ps = DBConnect.getInstance().get("insert into key_sign(id_user,public_key,date_add,date_lost,status) values (?,?,NOW(),NULL,?)");
        try {
            ps.setString(1, id_user);

            ps.setString(2, publickey);

            ps.setInt(3, status);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isValidDSAPublicKeyBase64(String base64PublicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("DSA");

            // Decode Base64 public key
            byte[] decodedKey = Base64.getDecoder().decode(base64PublicKey);

            // Create DSAPublicKeySpec from decoded key
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
            PublicKey publicKey = keyFactory.generatePublic(keySpec);

            // Perform additional checks if needed
            // For example, you may want to ensure that the key is of DSA type
            // or check other properties

            return true; // Return true if the key is valid
        } catch (Exception e) {
            // Handle any exceptions (e.g., invalid key format, algorithm mismatch)
            return false; // Return false if the key is invalid
        }
    }


    public int CheckCreateKey(String id_user) {
        int result = 0;
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select count(*) from key_sign where key_sign.status=1 and key_sign.id_user = ?");
            ps.setString(1, id_user);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt(1);
                rs.close();
                ps.close();
                return result;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public String getpublickey(String id_user) {
        String result = "";
        try {
            PreparedStatement ps = DBConnect.getInstance().get("select key_sign.public_key from key_sign where key_sign.id_user=? and key_sign.status=1");
            ps.setString(1, id_user);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getString(1);
                rs.close();
                ps.close();
                return result;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }



    // Phương thức để tạo cặp khóa
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        keyPairGenerator.initialize(1024    ); // Độ dài của khóa, có thể điều chỉnh theo yêu cầu
        return keyPairGenerator.generateKeyPair();
    }

    // Phương thức để tạo chữ ký số
    public static String signData(String data, String privateKeyBase64) throws Exception {
        PrivateKey privateKey = getPrivateKey(privateKeyBase64);
        Signature signature = Signature.getInstance("SHA256withDSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes());
        byte[] signatureBytes = signature.sign();
        return encodeBase64(signatureBytes);
    }

    // Phương thức để xác minh chữ ký số
    public static boolean verifySignature(String data, String signatureBase64, String publicKeyBase64) throws Exception {
        PublicKey publicKey = getPublicKey(publicKeyBase64);
        Signature verifier = Signature.getInstance("SHA256withDSA");
        verifier.initVerify(publicKey);
        verifier.update(data.getBytes());
        byte[] signatureBytes = decodeBase64(signatureBase64);
        return verifier.verify(signatureBytes);
    }

    // Phương thức để chuyển đổi mảng byte thành chuỗi hex
    private static String encodeBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    // Phương thức để chuyển đổi chuỗi hex thành mảng byte
    private static byte[] decodeBase64(String base64) {
        return Base64.getDecoder().decode(base64);
    }

    // Phương thức để chuyển đổi chuỗi base64 thành PrivateKey
    private static PrivateKey getPrivateKey(String privateKeyBase64) throws Exception {
        byte[] privateKeyBytes = decodeBase64(privateKeyBase64);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("DSA");
        return keyFactory.generatePrivate(keySpec);
    }

    // Phương thức để chuyển đổi chuỗi base64 thành PublicKey
    private static PublicKey getPublicKey(String publicKeyBase64) throws Exception {
        byte[] publicKeyBytes = decodeBase64(publicKeyBase64);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("DSA");
        return keyFactory.generatePublic(keySpec);
    }

    public static String[] generateKeyPairBase64() throws NoSuchAlgorithmException {
        KeyPair keyPair = generateKeyPair();
        String privateKeyBase64 = encodeBase64(keyPair.getPrivate().getEncoded());
        String publicKeyBase64 = encodeBase64(keyPair.getPublic().getEncoded());
        return new String[]{privateKeyBase64, publicKeyBase64};
    }


    public static void main(String[] args) throws NoSuchAlgorithmException {
//        KeyDao keydao = new KeyDao();
//        keydao.addDB("TK100","HD300", "MIIDQjCCAjUGByqGSM44BAEwggIoAoIBAQCPeTXZuarpv6vtiHrPSVG28y7FnjuvNxjo6sSWHz79NgbnQ1GpxBgzObgJ58KuHFObp0dbhdARrbi0eYd1SYRpXKwOjxSzNggooi/6JxEKPWKpk0U0CaD+aWxGWPhL3SCBnDcJoBBXsZWtzQAjPbpUhLYpH51kjviDRIZ3l5zsBLQ0pqwudemYXeI9sCkvwRGMn/qdgYHnM423krcw17njSVkvaAmYchU5Feo9a4tGU8YzRY+AOzKkwuDycpAlbk4/ijsIOKHEUOThjBopo33fXqFD3ktm/wSQPtXPFiPhWNSHxgjpfyEc2B3KI8tuOAdl+CLjQr5ITAV2OTlgHNZnAh0AuvaWpoV499/e5/pnyXfHhe8ysjO65YDAvNVpXQKCAQAWplxYIEhQcE51AqOXVwQNNNo6NHjBVNTkpcAtJC7gT5bmHkvQkEq9rI837rHgnzGC0jyQQ8tkL4gAQWDt+coJsyB2p5wypifyRz6Rh5uixOdEvSCBVEy1W4AsNo0fqD7UielOD6BojjJCilx4xHjGjQUntxyaOrsLC+EsRGiWOefTznTbEBplqiuH9kxoJts+xy9LVZmDS7TtsC98kOmkltOlXVNb6/xF1PYZ9j897buHOSXC8iTgdzEpbaiH7B5HSPh++1/et1SEMWsiMt7lU92vAhErDR8C2jCXMiT+J67ai51LKSLZuovjntnhA6Y8UoELxoi34u1DFuHvF9veA4IBBQACggEARn9ao8+vHA9Rb6k/a1zTUMmCEOtJEZ10W7aV/iRGYjJrLnFmXRvacSlorrz3XFD8iC+tzdcfW4qhDqOMV0umNoCc+VhzEEt+/lLvvdYBJQlBJB74G7IPsnN911BlgUkIt+1JrN7/Hdc6sgNQA79xUVL5ilzTuTDiDIcFb5v/zMkTTBFSNbwRW9z5iGa2MQ4PLeNRQgjRzn+U+tKjVM1DFP5dCQ00N2xCv2YRg1v8pWe3pRVlMqbJXxu3NkJ+G2YRzBL0vt+/OMN81NA2SLFQhqVC3FXQrzRByp0lGvep1jy1i+ChMjR/NRY/2hezQuYkkiGIwGIaISeEEja2HAHDbQ==",1);
//KeyDao.getInstance().addDB("TK101", "MIIDQjCCAjUGByqGSM44BAEwggIoAoIBAQCPeTXZuarpv6vtiHrPSVG28y7FnjuvNxjo6sSWHz79NgbnQ1GpxBgzObgJ58KuHFObp0dbhdARrbi0eYd1SYRpXKwOjxSzNggooi/6JxEKPWKpk0U0CaD+aWxGWPhL3SCBnDcJoBBXsZWtzQAjPbpUhLYpH51kjviDRIZ3l5zsBLQ0pqwudemYXeI9sCkvwRGMn/qdgYHnM423krcw17njSVkvaAmYchU5Feo9a4tGU8YzRY+AOzKkwuDycpAlbk4/ijsIOKHEUOThjBopo33fXqFD3ktm/wSQPtXPFiPhWNSHxgjpfyEc2B3KI8tuOAdl+CLjQr5ITAV2OTlgHNZnAh0AuvaWpoV499/e5/pnyXfHhe8ysjO65YDAvNVpXQKCAQAWplxYIEhQcE51AqOXVwQNNNo6NHjBVNTkpcAtJC7gT5bmHkvQkEq9rI837rHgnzGC0jyQQ8tkL4gAQWDt+coJsyB2p5wypifyRz6Rh5uixOdEvSCBVEy1W4AsNo0fqD7UielOD6BojjJCilx4xHjGjQUntxyaOrsLC+EsRGiWOefTznTbEBplqiuH9kxoJts+xy9LVZmDS7TtsC98kOmkltOlXVNb6/xF1PYZ9j897buHOSXC8iTgdzEpbaiH7B5HSPh++1/et1SEMWsiMt7lU92vAhErDR8C2jCXMiT+J67ai51LKSLZuovjntnhA6Y8UoELxoi34u1DFuHvF9veA4IBBQACggEARn9ao8+vHA9Rb6k/a1zTUMmCEOtJEZ10W7aV/iRGYjJrLnFmXRvacSlorrz3XFD8iC+tzdcfW4qhDqOMV0umNoCc+VhzEEt+/lLvvdYBJQlBJB74G7IPsnN911BlgUkIt+1JrN7/Hdc6sgNQA79xUVL5ilzTuTDiDIcFb5v/zMkTTBFSNbwRW9z5iGa2MQ4PLeNRQgjRzn+U+tKjVM1DFP5dCQ00N2xCv2YRg1v8pWe3pRVlMqbJXxu3NkJ+G2YRzBL0vt+/OMN81NA2SLFQhqVC3FXQrzRByp0lGvep1jy1i+ChMjR/NRY/2hezQuYkkiGIwGIaISeEEja2HAHDbQ==",1);
//int a=KeyDao.getInstance().CheckCreateKey("TK11");
//        System.out.println(a);

            	 String[] keyPairBase64 = generateKeyPairBase64();
    	 System.out.println("private key: "+keyPairBase64[0]);
    	 System.out.println("public key: "+keyPairBase64[1]);




//        try {
//            // Tạo cặp khóa (public key và private key)
//            KeyPair keyPair = generateKeyPair();
//
//            // Chuỗi dữ liệu cần ký
//            String dataToSign = "Hello, Digital Signature!";
//            String dataToSign2 = "Hello, Digital Signature!";
//
//            // Tạo chữ ký số
//            String privateKeyBase64 = encodeBase64(keyPair.getPrivate().getEncoded());
//            String signatureBase64 = signData(dataToSign, "MIICXAIBADCCAjUGByqGSM44BAEwggIoAoIBAQCPeTXZuarpv6vtiHrPSVG28y7FnjuvNxjo6sSWHz79NgbnQ1GpxBgzObgJ58KuHFObp0dbhdARrbi0eYd1SYRpXKwOjxSzNggooi/6JxEKPWKpk0U0CaD+aWxGWPhL3SCBnDcJoBBXsZWtzQAjPbpUhLYpH51kjviDRIZ3l5zsBLQ0pqwudemYXeI9sCkvwRGMn/qdgYHnM423krcw17njSVkvaAmYchU5Feo9a4tGU8YzRY+AOzKkwuDycpAlbk4/ijsIOKHEUOThjBopo33fXqFD3ktm/wSQPtXPFiPhWNSHxgjpfyEc2B3KI8tuOAdl+CLjQr5ITAV2OTlgHNZnAh0AuvaWpoV499/e5/pnyXfHhe8ysjO65YDAvNVpXQKCAQAWplxYIEhQcE51AqOXVwQNNNo6NHjBVNTkpcAtJC7gT5bmHkvQkEq9rI837rHgnzGC0jyQQ8tkL4gAQWDt+coJsyB2p5wypifyRz6Rh5uixOdEvSCBVEy1W4AsNo0fqD7UielOD6BojjJCilx4xHjGjQUntxyaOrsLC+EsRGiWOefTznTbEBplqiuH9kxoJts+xy9LVZmDS7TtsC98kOmkltOlXVNb6/xF1PYZ9j897buHOSXC8iTgdzEpbaiH7B5HSPh++1/et1SEMWsiMt7lU92vAhErDR8C2jCXMiT+J67ai51LKSLZuovjntnhA6Y8UoELxoi34u1DFuHvF9veBB4CHFgZv4bKqFrNgL7dqTP1GM7jyVsBVT/i6J7AbLA=");
//
//            // In chữ ký số ra màn hình
//            System.out.println("Digital Signature: " + signatureBase64);
//
//            // Xác minh chữ ký số
//            String publicKeyBase64 = encodeBase64(keyPair.getPublic().getEncoded());
//            boolean verified = verifySignature(dataToSign2, signatureBase64, "MIIDQjCCAjUGByqGSM44BAEwggIoAoIBAQCPeTXZuarpv6vtiHrPSVG28y7FnjuvNxjo6sSWHz79NgbnQ1GpxBgzObgJ58KuHFObp0dbhdARrbi0eYd1SYRpXKwOjxSzNggooi/6JxEKPWKpk0U0CaD+aWxGWPhL3SCBnDcJoBBXsZWtzQAjPbpUhLYpH51kjviDRIZ3l5zsBLQ0pqwudemYXeI9sCkvwRGMn/qdgYHnM423krcw17njSVkvaAmYchU5Feo9a4tGU8YzRY+AOzKkwuDycpAlbk4/ijsIOKHEUOThjBopo33fXqFD3ktm/wSQPtXPFiPhWNSHxgjpfyEc2B3KI8tuOAdl+CLjQr5ITAV2OTlgHNZnAh0AuvaWpoV499/e5/pnyXfHhe8ysjO65YDAvNVpXQKCAQAWplxYIEhQcE51AqOXVwQNNNo6NHjBVNTkpcAtJC7gT5bmHkvQkEq9rI837rHgnzGC0jyQQ8tkL4gAQWDt+coJsyB2p5wypifyRz6Rh5uixOdEvSCBVEy1W4AsNo0fqD7UielOD6BojjJCilx4xHjGjQUntxyaOrsLC+EsRGiWOefTznTbEBplqiuH9kxoJts+xy9LVZmDS7TtsC98kOmkltOlXVNb6/xF1PYZ9j897buHOSXC8iTgdzEpbaiH7B5HSPh++1/et1SEMWsiMt7lU92vAhErDR8C2jCXMiT+J67ai51LKSLZuovjntnhA6Y8UoELxoi34u1DFuHvF9veA4IBBQACggEAfzFvVbFLr8+7DEKCidAaIsP1pLfGjIrRWW6GH7b2VvcrHOhzBREzWIGA+9oaV+MYOYTrSR8YaQO3sGS4roGXN3WPuJHXs6Fr8Gj15aFxy17Sy5hIhuKg8Lh0aCug5b3irRT/mFR52ga7jpQwVvIfrmVQwy3iNu/dIX9LN9oKDupn+J6DmLrO5lUY9i5ygkBASf5Ci/AqTGrCH8/PrtPIJIuJoxrnRRDhYmEwU7vfF3zDuR8iILN4hvosTgR4qRRWR3lGYz6BHDL7laNwnTM4TwERfKvjoTIxoiOfvRuvHaj8VWtAteMyA+vJk3Ld7+Se7MqnTecFLMJ0pW27V71pTA==");
//
//            // In kết quả xác minh
//            System.out.println("Signature Verified: " + verified);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }



//        String base64PublicKey = "MIIBuDCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoDgYUAAoGBAM7+4AH+pfiSOvTmmcYcVWExNs/C/IJ5XjiwezK7skKxjw5SrcJ2rLGtf14pY7sSAsvTNAHi/g8eABU/+KLY9mk0rZAnj0LpwTIT/HLrgT5FtiNBZdm+ZZJw54QpN4hcCTCWWEej3rVrGecQUv3YN1On9uo0uRQiKAjy9gXJEjex";
//
//
//        boolean isValid = isValidDSAPublicKeyBase64(base64PublicKey);
//
//        System.out.println("Is public key valid? " + isValid);
    }
}


