import java.util.Arrays;


public class MergeArray {

	public static void main(String[] args){
		int[] firstArray = {4,8,9,10,45,54,69,85,214,3131};
		int[] secondArray = {1,2,6,9,78,251,297,355,875,4514};
		System.out.println(Arrays.toString(merge(firstArray,secondArray)));
	}
	
	public static int[] merge(int[] first, int[] second) {
	
	    int[] answer = new int[first.length + second.length];
	    int i = 0, j = 0, k = 0;
	
	    while (i < first.length && j < second.length)  
	       answer[k++] = first[i] < second[j] ? first[i++] :  second[j++];
	
	    while (i < first.length)  
	        answer[k++] = first[i++];
	
	
	    while (j < second.length)    
	        answer[k++] = second[j++];
	
	    return answer;
	}
}
