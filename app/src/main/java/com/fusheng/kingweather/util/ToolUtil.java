package com.fusheng.kingweather.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.Layout;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.ReplacementTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ToolUtil extends ReplacementTransformationMethod {
    private static long lastClickTime = 0;
    private static long min_click_delay_time = 300;
    private static Pattern VERIFY_IC_NUMBER = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
    //原来使用的检验手机号标准"^((13[0-9])|(14[0-9])|(15[^4,\\D])|(18[0-3,5-9])|(17[0-9]))\\d{8}$"
    private static Pattern VERIFY_MOBILE_PHONE_NUMBER = Pattern.compile("^1\\d{10}$");
    //    private static Pattern VERIFY_MOBILE_PHONE_NUMBER = Pattern.compile("^1([34578][0-9])\\d{8}$");
    private static Pattern VERIFY_BINKCARD_NUMBER = Pattern.compile("^\\d{16,19}$|^\\d{6}[- ]\\d{10,13}$|^\\d{4}[- ]\\d{4}[- ]\\d{4}[- ]\\d{4,7}$");
    private static Pattern VERIFY_MOBILE_TYPE = Pattern.compile("^(([4,8]00))\\d{7}$");
    private static Pattern VERIFY_CHINA_MOBILE = Pattern.compile("^((13[4-9])|(147)|(15[0-2,7-9])|(18[2,3,7,8]))\\d{8}$");
    private static Pattern VERIFY_CHINA_UNICOM = Pattern.compile("^((13[0-2])|(145)|(15[5,6])|(18[5,6]))\\d{8}$");
    private static Pattern VERIFY_CHINA_TELECOM = Pattern.compile("^((1[3,5]3)|(18[0,1,9]))\\d{8}$");
    private static Pattern VERIFY_VIRTUAL = Pattern.compile("^((17[0-9]))\\d{8}$");
    private static Pattern VERIFY_POSTAL_CODE = Pattern.compile("[0-9]\\d{5}");
    private static Pattern VERIFY_POSTAL_TYPE = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
    private static Pattern VERIFY_FIX_PHONE = Pattern.compile("d{3}-d{8}|d{4}-d{7}");

    /**
     * 判断是否是身份证
     * @param identifyCard
     */
    public static boolean isIdentifyCard(String identifyCard) {
        Matcher m = VERIFY_IC_NUMBER.matcher(identifyCard);
        return m.matches();
    }

    /**
     * 判断电话号码是否有效
     * 移动：134、135、136、137、138、139、147、150、151、152、157、158、159、182、183、187、188
     * 联通：130、131、132、145、155、156、185、186
     * 电信：133、153、180、181、189
     * 虚拟运营商：17x
     */
    public static boolean isMobileNO(String number) {
        if (number.startsWith("+86")) {
            number = number.substring(3);
        }
        if (number.startsWith("+") || number.startsWith("0")) {
            number = number.substring(1);
        }
        number = number.replace(" ", "").replace("-", "");
        Matcher m = VERIFY_MOBILE_PHONE_NUMBER.matcher(number);
        return m.matches();
    }

    /**
     * 匹配车牌号码格式
     */
    public static boolean isCarNum(String number) {
        String carNum = "(^([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}(([0-9]{5}[DF])|([DF]([A-HJ-NP-Z0-9])[0-9]{4})))$)|(^([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳]{1})$)";
        Pattern p = Pattern.compile(carNum);
        Matcher m = p.matcher(number);
        return m.matches();
    }

    /**
     * 检验VIN格式
     * @param vin
     * @return
     */
    public static boolean checkVIN(String vin) {
        Map<Integer, Integer> vinMapWeighting = null;
        Map<Character, Integer> vinMapValue = null;
        vinMapWeighting = new HashMap<Integer, Integer>();
        vinMapValue = new HashMap<Character, Integer>();
        vinMapWeighting.put(1, 8);
        vinMapWeighting.put(2, 7);
        vinMapWeighting.put(3, 6);
        vinMapWeighting.put(4, 5);
        vinMapWeighting.put(5, 4);
        vinMapWeighting.put(6, 3);
        vinMapWeighting.put(7, 2);
        vinMapWeighting.put(8, 10);
        vinMapWeighting.put(9, 0);
        vinMapWeighting.put(10, 9);
        vinMapWeighting.put(11, 8);
        vinMapWeighting.put(12, 7);
        vinMapWeighting.put(13, 6);
        vinMapWeighting.put(14, 5);
        vinMapWeighting.put(15, 4);
        vinMapWeighting.put(16, 3);
        vinMapWeighting.put(17, 2);

        vinMapValue.put('0', 0);
        vinMapValue.put('1', 1);
        vinMapValue.put('2', 2);
        vinMapValue.put('3', 3);
        vinMapValue.put('4', 4);
        vinMapValue.put('5', 5);
        vinMapValue.put('6', 6);
        vinMapValue.put('7', 7);
        vinMapValue.put('8', 8);
        vinMapValue.put('9', 9);
        vinMapValue.put('A', 1);
        vinMapValue.put('B', 2);
        vinMapValue.put('C', 3);
        vinMapValue.put('D', 4);
        vinMapValue.put('E', 5);
        vinMapValue.put('F', 6);
        vinMapValue.put('G', 7);
        vinMapValue.put('H', 8);
        vinMapValue.put('J', 1);
        vinMapValue.put('K', 2);
        vinMapValue.put('M', 4);
        vinMapValue.put('L', 3);
        vinMapValue.put('N', 5);
        vinMapValue.put('P', 7);
        vinMapValue.put('R', 9);
        vinMapValue.put('S', 2);
        vinMapValue.put('T', 3);
        vinMapValue.put('U', 4);
        vinMapValue.put('V', 5);
        vinMapValue.put('W', 6);
        vinMapValue.put('X', 7);
        vinMapValue.put('Y', 8);
        vinMapValue.put('Z', 9);
        boolean reultFlag = false;
        String uppervin = vin.toUpperCase();
        //排除字母O、I
        if (vin == null || uppervin.indexOf("O") >= 0 || uppervin.indexOf("I") >= 0) {
            reultFlag = false;
        } else {
            //1:长度为17
            if (vin.length() == 17) {
                char[] vinArr = uppervin.toCharArray();
                int amount = 0;
                for (int i = 0; i < vinArr.length; i++) {
                    //VIN码从从第一位开始，码数字的对应值×该位的加权值，计算全部17位的乘积值相加
                    amount += vinMapValue.get(vinArr[i]) * vinMapWeighting.get(i + 1);
                }
                //乘积值相加除以11、若余数为10，即为字母Ｘ
                if (amount % 11 == 10) {
                    if (vinArr[8] == 'X') {
                        reultFlag = true;
                    } else {
                        reultFlag = false;
                    }

                } else {
                    //VIN码从从第一位开始，码数字的对应值×该位的加权值，//计算全部17位的乘积值相加除以11，所得的余数，即为第九位校验值
                    if (amount % 11 != vinMapValue.get(vinArr[8])) {
                        reultFlag = false;
                    } else {
                        reultFlag = true;
                    }
                }
            }
            //1:长度不为17
            if (!"".equals(vin) && vin.length() != 17) {
                reultFlag = false;
            }
        }
        return reultFlag;
    }

    /**
     * 验证银卡卡号
     * @param cardNo
     * @return
     */
    public static boolean isBankCard(String cardNo) {
        Matcher m = VERIFY_BINKCARD_NUMBER.matcher(cardNo);
        return m.matches();
    }

    /**
     * 号码的运营商类型
     * @param number
     * @return
     */
    public static String getMobileType(String number) {
        String type = "未知用户";
        if (VERIFY_MOBILE_TYPE.matcher(number).matches()) {
            return "企业电话";
        }
        if (number.startsWith("+86")) {
            number = number.substring(3);
        }

        if (number.startsWith("+") || number.startsWith("0")) {
            number = number.substring(1);
        }

        number = number.replace(" ", "").replace("-", "");
        System.out.print("号码：" + number);
        if (VERIFY_CHINA_MOBILE.matcher(number).matches()) {
            return "移动用户";
        }
        if (VERIFY_CHINA_UNICOM.matcher(number).matches()) {
            return "联通用户";
        }
        if (VERIFY_CHINA_TELECOM.matcher(number).matches()) {
            return "电信用户";
        }
        if (VERIFY_VIRTUAL.matcher(number).matches()) {
            return "虚拟运营端";
        }
        if (number.length() >= 7 && number.length() <= 12) {
            return "固话用户";
        }
        return type;
    }

    /**
     * 获取随机数
     * @param iRdLength
     * @return
     */
    public static String getRandom(int iRdLength) {
        Random rd = new Random();
        int iRd = rd.nextInt();
        if (iRd < 0) { // 负数时转换为正数
            iRd *= -1;
        }
        String sRd = String.valueOf(iRd);
        int iLgth = sRd.length();
        if (iRdLength > iLgth) { // 获取数长度超过随机数长度
            return digitToString(iRd, iRdLength);
        } else {
            return sRd.substring(iLgth - iRdLength, iLgth);
        }
    }

    /**
     * 把一个整数转化为一个n位的字符串
     * @param digit
     * @param n
     * @return
     */
    public static String digitToString(int digit, int n) {
        String result = "";
        for (int i = 0; i < n - String.valueOf(digit).length(); i++) {
            result = result + "0";
        }
        result = result + String.valueOf(digit);
        return result;
    }

    /**
     * 计算MD5
     * @param str
     * @return
     */
    public static String md5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            md.update(str.getBytes("utf-8"));
            byte[] result = md.digest();
            StringBuffer sb = new StringBuffer(32);
            for (int i = 0; i < result.length; i++) {
                int val = result[i] & 0xff;
                if (val <= 0xf) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(val));
            }
            return sb.toString();//.toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 四舍五入
     */
    public static String doubleConvert(double value) {
        long l1 = Math.round(value * 100);//四舍五入
        double ret = l1 / 100.0;//注意：使用100.0,而不是 100
        if (ret - (int) ret == 0) {
            return (int) ret + "";
        } else {
            return ret + "";
        }
    }

    /**
     * 保留俩位小数
     */
    public static String saveTwo(BigDecimal data) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(data);
    }

    /**
     * 保留俩位小数
     */
    public static String saveTwo(String data) {
        double v = Double.parseDouble(data);

        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(v);
    }

    /**
     * 返回String
     */
    public static String getText(TextView date) {
        return date.getText().toString().trim();
    }

    public static String getText(EditText date) {
        return date.getText().toString().trim();
    }

    /**
     * 根据字符串，计算出其占用的宽度
     * @param str
     * @param textsize
     * @return
     */
    public static float getTextWidthFontPx(String str, float textsize) {
        Paint mPaint = new Paint();
        mPaint.setTextSize(textsize);
        return str.length() * mPaint.getFontSpacing();
    }

    /**
     * 根据经纬度计算距离
     * @param longitude1
     * @param latitude1
     * @return
     */
    private static final double EARTH_RADIUS = 6378137.0;

    /**
     * 返回单位是米
     */
    public static double getDistance(double longitude1, double latitude1,
                                     double longitude2, double latitude2) {
        double lat1 = rad(latitude1);
        double lat2 = rad(latitude2);
        double a = lat1 - lat2;
        double b = rad(longitude1) - rad(longitude2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 是否含有指定字符
     * @param expression
     * @param text
     * @return
     */
    private static boolean matchingText(String expression, String text) {
        Pattern p = Pattern.compile(expression);
        Matcher m = p.matcher(text);
        boolean b = m.matches();
        return b;
    }

    /**
     * 邮政编码
     * @param zipcode
     * @return
     */
    public static boolean isZipcode(String zipcode) {
        Matcher m = VERIFY_POSTAL_CODE.matcher(zipcode);
        System.out.println(m.matches() + "-zipcode-");
        return m.matches();
    }

    /**
     * 邮件格式
     * @param email
     * @return
     */
    public static boolean isValidEmail(String email) {
        Matcher m = VERIFY_POSTAL_TYPE.matcher(email);
        System.out.println(m.matches() + "-email-");
        return m.matches();
    }

    /**
     * 固话号码格式
     * @param telfix
     * @return
     */
    public static boolean isTelfix(String telfix) {
        Matcher m = VERIFY_FIX_PHONE.matcher(telfix);
        System.out.println(m.matches() + "-telfix-");
        return m.matches();
    }


    /**
     * 计算剩余日期
     * @return
     */
    public static String calculationRemainTime(String endTime, long countDown) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            // 获取当前时间
            Date now = new Date(System.currentTimeMillis());
            Date endData = df.parse(endTime);
            long l = endData.getTime() - countDown - now.getTime();
            long day = l / (24 * 60 * 60 * 1000);
            long hour = (l / (60 * 60 * 1000) - day * 24);
            long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            return "剩余" + day + "天" + hour + "小时" + min + "分" + s + "秒";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void showLongToast(Context act, String pMsg) {
        Toast toast = Toast.makeText(act, pMsg, Toast.LENGTH_LONG);
        toast.show();
    }

    public static void showShortToast(Context act, String pMsg) {
        Toast toast = Toast.makeText(act, pMsg, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * 获取手机Imei号
     * @param context
     * @return
     */
   /* public static String getImeiCode(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }*/

    /**
     * @param listView
     * @author sunglasses
     * @category 计算listview的高度
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


    /**
     * 大小写字母转换
     */
    @Override
    protected char[] getOriginal() {
        char[] aa = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        return aa;
    }

    @Override
    protected char[] getReplacement() {
        char[] cc = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        return cc;
    }

    /**
     * 判断字符串是否为null的方法
     */
    public static boolean isStringNull(String str) {
        return str == null || "".equals(str) || " ".equals(str) || "[]".equals(str) || str.length() == 0;
    }

    /**
     * 防暴力点击方法
     */
    public static Boolean noDoubleClick() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - lastClickTime > min_click_delay_time) {
            lastClickTime = currentTimeMillis;
            //可以点击
            return true;
        }
        return false;
    }

    /**
     * 防暴力点击方法
     * @param spaceTime 间隔时间(ms)
     */
    public static Boolean noDoubleClick(long spaceTime) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - lastClickTime > spaceTime) {
            lastClickTime = currentTimeMillis;
            //可以点击
            return true;
        }
        return false;
    }

    /**
     * 将每三个数字加上逗号处理（通常使用金额方面的编辑）
     * @param str 需要处理的字符串
     * @return 处理完之后的字符串
     */
    public static String formatMoney(String str) {
        DecimalFormat myformat = new DecimalFormat();
        myformat.applyPattern("###.00");
        if (0 == convertToDouble(str, 0)) {
            return "0.00";
        }

        String formatData = myformat.format(convertToDouble(str, 0));
        int indexOf = formatData.indexOf(".");
        String substring = str.substring(0);
        String substring1 = str.substring(indexOf);
        if (substring.equals(substring1)) {
            return "0" + formatData;
        }
        return myformat.format(convertToDouble(str, 0));
    }

    /**
     * double转String,保留小数点后两位
     * @param num
     * @return
     */
    public static String doubleToString(double num){
        String strNum = String.valueOf(num);
        int n = strNum.indexOf(".");
        if(n>0){
            //截取小数点后的数字
            String dotNum = strNum.substring(n+1);
            if("0".equals(dotNum)){
                return strNum+"0";
            }else{
                if(dotNum.length()==1){
                    return strNum +"0";
                }else{
                    return strNum;
                }
            }
        }else{
            return strNum+".00";
        }
    }

    /**
     * double→格式化的String
     */
    public static String formatMoney(double d) {
        DecimalFormat myformat = new DecimalFormat();
        myformat.applyPattern("0.00");
        //myformat.applyPattern("###,###.00");
        if (0 == d) {
            return "0.00";
        }
        return myformat.format(d);
    }

    /**
     * 把String转化为float
     */
    public static float convertToFloat(String number, float defaultValue) {
        if (TextUtils.isEmpty(number)) {
            return defaultValue;
        }
        try {
            return Float.parseFloat(number);
        } catch (Exception e) {
            return defaultValue;
        }

    }

    /**
     * 把String转化为double
     */
    public static double convertToDouble(String number, double defaultValue) {
        if (TextUtils.isEmpty(number)) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(number);
        } catch (Exception e) {
            return defaultValue;
        }

    }

    /**
     * 钱的格式
     * @param money
     * @return
     */
    public static boolean checkMoney(String money) {
        boolean tag = true;
        if (".".equals(money.substring(0, 1))) {
            return false;
        }
        if (".".equals(money.substring(money.length() - 1, money.length()))) {
            return false;
        }
        int x = 0;
        for (int i = 0; i <= money.length() - 1; i++) {
            String getstr = money.substring(i, i + 1);
            if (".".equals(getstr)) {
                x = x + 1;
            }
        }
        if (x > 1) {
            return false;
        }
        return tag;
    }

    /**
     * 将String类型数据转换成long类型
     */
    public static Long getLongParseString(String str) {
        if (!isStringNull(str)) {
            return Long.parseLong(str);
        } else {
            return 0L;
        }
    }

    /**
     * 将String类型数据转换成int类型
     */
    public static int getIntParseString(String str) {
        if (!isStringNull(str)) {
            return Integer.parseInt(str);
        } else {
            return 0;
        }
    }

    /**
     * Uri 转 绝对路径
     */

    public static String getFilePathFromContentUri(Uri selectedVideoUri, ContentResolver contentResolver) {
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};

        Cursor cursor = contentResolver.query(selectedVideoUri, filePathColumn, null, null, null);
        //            也可用下面的方法拿到cursor
        //            Cursor  cursor  =  this.context.managedQuery(selectedVideoUri,  filePathColumn,  null,  null,  null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }

    /**
     * 是否关闭购物车
     * @param dowPartListSize 购物车中达欧配件列表的size
     * @param selfPartListSize 购物车中自采件配件列表的size
     * @return
     */
    public static boolean isCloseCart(int dowPartListSize, int selfPartListSize) {
        return dowPartListSize == 0 && selfPartListSize == 0;
    }


    /**
     * 小数转百分数
     * @param decimal 百分数字符串
     */
    public static String decimal2Percent2(String decimal) {
        DecimalFormat df = new DecimalFormat("0.00%");
        return df.format(decimal);
    }

    /**
     * 百分数转小数
     * @param percent 小数字符串
     */
    public static String percent2Decimal(String percent) {
        NumberFormat nf = NumberFormat.getPercentInstance();
        Number parse = null;
        try {
            parse = nf.parse(percent);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse == null ? "0" : parse.toString();
    }

    /**
     * 获取当前日期--（格式2017-12-06）
     */
    public static String getCurrentTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(Calendar.getInstance().getTime());
    }

    /**
     * 输入时间加上180天再格式化
     * @param timeString 输入的时间(格式：2017-12-12)
     * @return 加过180天的时间
     */
    public static String getIncreaseTime(String timeString) {
        String timeStamp = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d;
        try {
            d = sdf.parse(timeString);
            //输入的时间加上180天的时间戳
            long l = d.getTime() + 15552000000L;
            //单位秒
            timeStamp = sdf.format(new Date(l));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timeStamp;
    }

    /**
     * 输入时间加上1天再格式化
     * @param timeString 输入的时间(格式：2017-12-12)
     * @return 加过1天的时间
     */
    public static String getOneDayIncreaseTime(String timeString) {
        String timeStamp = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d;
        try {
            d = sdf.parse(timeString);
            //输入的时间加上1天的时间戳
            long l = d.getTime() + 86400000L;
            //单位秒
            timeStamp = sdf.format(new Date(l));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timeStamp;
    }

    /**
     * 保留两位小数
     * @param s
     * @param et
     */
    public static void keep2Decimal(TextWatcher textWatcher, CharSequence s, EditText et) {
        if (s.toString().contains(".")) {
            if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                s = s.toString().subSequence(0, s.toString().indexOf(".") + 3);
                et.removeTextChangedListener(textWatcher);
                et.setText(s);
                et.setSelection(s.length());
                et.addTextChangedListener(textWatcher);
            }
        }
        if (".".equals(s.toString())) {
            s = "0" + s;
            et.removeTextChangedListener(textWatcher);
            et.setText(s);
            et.setSelection(2);
            et.addTextChangedListener(textWatcher);
        }
        if (s.toString().startsWith("0") && s.toString().trim().length() > 1 && !".".equals(s.toString().substring(1, 2))) {
            et.removeTextChangedListener(textWatcher);
            et.setText(s.toString().substring(1, s.length()));
            et.setSelection(s.length() - 1);
            et.addTextChangedListener(textWatcher);
        }
    }


    /**
     * 移除String为null的情况，避免展示到UI上
     * @param str
     * @return
     */
    public static String removeNull(String str) {
        return str == null ? "" : str;
    }

    /**
     * 这是一个查看图片大小的，我之后可能进行压缩，或许大家之后也会用到，就贴在这里
     */
    public int getBitmapSize(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return bitmap.getAllocationByteCount();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            return bitmap.getByteCount();
        } else {
            return bitmap.getRowBytes() * bitmap.getHeight();
        }
    }

    /**
     * 半角转全角，解决TextView显示中文参差不齐问题
     */
    public static String toDBC(String text) {
        char[] c = text.toCharArray();
        for (int i = 0; i < c.length; i++) {
            // 全角空格为12288，半角空格为32
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            // 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
            if (c[i] > 65280 && c[i] < 65375) {
                c[i] = (char) (c[i] - 65248);
            }
        }
        return new String(c);
    }

    /**
     * 获取TextView的某一个字符的X坐标
     */
    public static int getTvLocationX(final TextView tv, int index) {
        Layout layout = tv.getLayout();
        Rect bound = new Rect();
        int line = layout.getLineForOffset(index);
        layout.getLineBounds(line, bound);
        //字符底部y坐标
        //int yAxisBottom = bound.bottom;
        //字符顶部y坐标
        //int yAxisTop = bound.top;
        //字符左边x坐标
        //float xAxisLeft = layout.getPrimaryHorizontal(index);
        //字符右边x坐标
        int xAxisRight = (int) layout.getSecondaryHorizontal(index);
        return xAxisRight;
    }

    /**
     * 获取TextView的某一个字符的Y坐标
     */
    public static int getTvLocationY(TextView tv, int index) {
        Layout layout = tv.getLayout();
        Rect bound = new Rect();
        int line = layout.getLineForOffset(index);
        layout.getLineBounds(line, bound);
        //字符底部y坐标
        //int yAxisBottom = bound.bottom;
        //字符顶部y坐标
        int yAxisTop = bound.top;
        //字符左边x坐标
        //float xAxisLeft = layout.getPrimaryHorizontal(index);
        //字符右边x坐标
        //int xAxisRight = (int) layout.getSecondaryHorizontal(index);
        return yAxisTop;
    }

}
