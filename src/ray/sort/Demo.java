package ray.sort;

public class Demo {

        public int[] heapSort( int[] nums) {
            int N = nums.length-1;
            for(int k = N/2-1; k >= 0 ; k--){
                sink(nums,k,N);
            }
            while(N > 0){
                exch(nums,0,N--);
                sink(nums,0,N);
            }
            return nums;
        }

        private void sink(int[] nums, int k, int N){
            while(2*k+1 <= N){
                int j = 2*k+1;
                if(j+1 <= N && less(nums,j,j+1))
                    j = j+1;
                if(!less(nums,k,j))
                    break;
                exch(nums, k, j);
                k=j;
            }
        }

        private void exch(int[] nums, int i, int j){
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

        private boolean less(int[] nums, int i, int j){
            return nums[i] < nums[j];
        }

    public static void main(String[] args) {
        Demo test = new Demo();
        int[] ints = test.heapSort(new int[]{2,1,2,1,8,6,2,5,4,9,3,7,4,5,9,1});
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }
}
