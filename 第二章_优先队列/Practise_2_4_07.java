package 第二章_优先队列;

public class Practise_2_4_07 {
    public static void main(String[] args) {
        /*
         * 脑补一棵二叉树的图
         * 
         * k = 2 第二大的位置只可能出现在索引为 2, 3 的位置上，其他位置都不可能出现
         * k = 3 第三大的位置可能出现在第二层的 2, 3 结点中，也可能作为第二大结点的子结点出现在第三层，但不可能出现在第四层以及往后的位置
         * k = 4 第四大可能作为根结点的子结点出现在第二层，也可能作为第三大或者第二大的子结点出现在第三层，也可能作为第三大的子结点出现在第四层，但不可能出现在第五层以及往后的位置
         * 
         * 
         * 综上所述，第二大可能出现的位置是 {2, 3}
         * 第三大可能出现的位置是 {2, 7}
         * 第四大可能出现的位置是 {2, 15}
         */
    }
}
