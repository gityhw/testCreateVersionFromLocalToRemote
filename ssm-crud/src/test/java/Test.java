import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;


public class Test {
	public static void main(String[] args){
		String pathFirst="D:\\a.xls";
		String pathSecond="D:\\b.xls";
		
		File fileFirst=new File(pathFirst);
		File fileSecond=new File(pathSecond);
		
		String firstMD5=getFileMD5(fileFirst);
		String secondMD5=getFileMD5(fileSecond);
		
		System.out.println(firstMD5.equals(secondMD5));
		
		Integer arr[] = {2, 4, 7, 6, 8, 5, 9};
        //SortUtil.show(arr);
		Test bubbleSort = new Test();
        //bubbleSort.bubbleSort(arr, arr.length);
        //SortUtil.show(arr);
        sort(arr);
//        List<Integer> list= Arrays.asList(arr);
//        System.out.println(list);
        System.out.println(Arrays.toString(arr));
	}

	/**
	 * 冒泡排序
	 * @param arr
	 * @param n
	 */
	public void bubbleSort(Integer[] arr, int n) {
        if (n <= 1) return;       //如果只有一个元素就不用排序了
 
        for (int i = 0; i < n; ++i) {
            // 提前退出冒泡循环的标志位,即一次比较中没有交换任何元素，这个数组就已经是有序的了
            boolean flag = false;
            for (int j = 0; j < n - i - 1; ++j) {        //此处你可能会疑问的j<n-i-1，因为冒泡是把每轮循环中较大的数飘到后面，
                // 数组下标又是从0开始的，i下标后面已经排序的个数就得多减1，总结就是i增多少，j的循环位置减多少
                if (arr[j] > arr[j + 1]) {        //即这两个相邻的数是逆序的，交换
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) break;//没有数据交换，数组已经有序，退出排序
        }
    }
 
	public static void sort(Integer[] arr){
		for(int i=0 ; i<arr.length ; i++){
			int index = i;
			for(int j=i+1 ; j<arr.length ; j++){
				if(arr[j]<arr[index]){
					index = j;
				}
			}
			
			if(index != i){
				int temp = arr[i];
				arr[i] = arr[index];
				arr[index] = temp;
			}
		}
		/*int i,j,index;
		for(i=0;i<array.length;i++) {
			index=i;
			for(j=i+1;j<array.length;j++) {
				if(array[j]<array[index]) {
					index=j;
				}
			}
			int temp=array[i];
			array[i]=array[index];
			array[index]=temp;
		}*/
		
	}
	
	/**
	 * 盐值加密返回加密值
	 * @param file
	 * @return
	 */
	public static String getFileMD5(File file){
		if (!file.isFile()) {
	        return null;
	    }
	    MessageDigest digest = null;
	    FileInputStream in = null;
	    byte buffer[] = new byte[8192];
	    int len;
	    try {
	        digest =MessageDigest.getInstance("MD5");
	        in = new FileInputStream(file);
	        while ((len = in.read(buffer)) != -1) {
	            digest.update(buffer, 0, len);
	        }
	        BigInteger bigInt = new BigInteger(1, digest.digest());
	        return bigInt.toString(16);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        try {
	            in.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

}
