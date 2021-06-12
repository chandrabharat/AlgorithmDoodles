class NumArray {
    int[] sol;
    public NumArray(int[] nums) {
        sol = new int[nums.length];
        sol[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sol[i] = sol[i - 1] + nums[i];
        }
    }


    public int sumRange(int i, int j) {
        int endIndex = i - 1 >= 0 ? sol[i - 1] : 0;
        return sol[j] - endIndex;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */