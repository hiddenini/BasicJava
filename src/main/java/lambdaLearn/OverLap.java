package lambdaLearn;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class OverLap {
	public static void main(String[] args) {
		LinkedList<JudgeVo> list=new LinkedList<JudgeVo>();

        JudgeVo b=new JudgeVo();
        b.setMin(9l);
        b.setMax(20l);
        list.add(b);

		JudgeVo a=new JudgeVo();
		a.setMin(1l);
		a.setMax(10l);
		list.add(a);

		JudgeVo c=new JudgeVo();
		c.setMin(21l);
		c.setMax(30l);
		list.add(c);
		//checkOverlap(list);
        System.out.println(check(list));
    }
	private  static void checkOverlap2(List<JudgeVo> list) {
        list.stream().sorted(Comparator.comparing(JudgeVo::getMin))
            .reduce((a, b) -> {
                if (a.getMax() >= b.getMin()) {
					//throw new Exception("区间有重叠");
					System.out.println("区间有重叠");
				}
                return b;
            });
    }

	private static void checkOverlap(LinkedList<JudgeVo> activeExtendDOList) {
		/**
		 * 将list按照min从小到大排序
		 */
		activeExtendDOList.sort(Comparator.comparing(JudgeVo::getMin));
		/**
		 * 拿出最小的那个dto的min,并将其remove
		 */
		Long temp = activeExtendDOList.poll().getMax();
		for (JudgeVo activeExtend : activeExtendDOList) {
			if (temp >= activeExtend.getMin()) {
				//throw new Exception("区间有重叠");
				System.out.println("区间有重叠");

			}
			temp = activeExtend.getMax();
		}
	}

    /**
     * 如果区间有重叠返回true
     * @param activeExtendDOList
     * @return
     */
    private static Boolean check(LinkedList<JudgeVo> activeExtendDOList) {
        /**
         * 将list按照min从小到大排序
         */
        activeExtendDOList.sort(Comparator.comparing(JudgeVo::getMin));
        /**
         * 拿出最小的那个dto的min,并将其remove
         */
        Long temp = activeExtendDOList.poll().getMax();
        for (JudgeVo activeExtend : activeExtendDOList) {
            if (temp >= activeExtend.getMin()) {
                //throw new Exception("区间有重叠");
                System.out.println("区间有重叠");
                return true;
            }
            temp = activeExtend.getMax();
        }
        return false;
    }
}
