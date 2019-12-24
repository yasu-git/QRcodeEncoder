package qr;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeEncoder {

    //sourceでQRコードを制止する値
    //nameで保存するファイル名

    public static String QRcodCreate  (String source ,String Name )throws NullPointerException{

        //QRコード生成時のエンコーディング
        String encoding = "UTF-8";
        //サイズ(ピクセル)
        int size = 100;
        //画像ファイル名前
        String fileName = Name +".png";

        //生成処理
        ConcurrentHashMap hints = new ConcurrentHashMap();
        //エラー訂正レベル指定
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        //エンコーディング指定
        hints.put(EncodeHintType.CHARACTER_SET, encoding);
        //マージン指定
        hints.put(EncodeHintType.MARGIN, 0);
        QRCodeWriter writer = new QRCodeWriter();

        BitMatrix bitMatrix;

        try {
            //QRコードの生成しbitMatrixに保存
            bitMatrix = writer.encode(source, BarcodeFormat.QR_CODE, size, size, hints);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

            //ファイルへの保存処理
            //QRコードimageをpng型でfileoutputStreamで指定した場所に保存する
            ImageIO.write(image, "png", new FileOutputStream("WebContent/image/" +fileName));
        } catch (WriterException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }

        return fileName;

    }

}
