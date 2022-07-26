package spring.mvc.session09.controller;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/lotto")
public class LottoController {

	// 存放歷史紀錄
	private List<Set<Integer>> lottos = new CopyOnWriteArrayList<>();

	// Lotto 主畫面
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("stat", get());
		model.addAttribute("stat2", getMap());
		model.addAttribute("lottos", lottos);
		return "session09/lotto";
	}

	// 取得最新電腦選好
	@RequestMapping("/get")
	public String get(Model model) {
		// 取得樂透號碼
		Set<Integer> lotto = getRandomLotto();
		// 將最新 lotto 號碼放入紀錄中
		lottos.add(lotto);
		// 將必要資訊傳給 jsp 呈現/處理
		model.addAttribute("stat", get());
		model.addAttribute("stat2", getMap());
		model.addAttribute("lotto", lotto);
		model.addAttribute("lottos", lottos);
		System.out.println("歷史資料 :" + lottos);
		return "session09/lotto";
	}

	@RequestMapping("/update/{index}")
	public String update(@PathVariable("index") Integer index, Model model) {
		Set<Integer> lotto = getRandomLotto();
		lottos.set(index, lotto);
		model.addAttribute("stat", get());
		model.addAttribute("stat2", getMap());
		model.addAttribute("lotto", lotto); // 最新電腦選號
		model.addAttribute("lottos", lottos); // 歷史紀錄
		return "redirect:../";
	}

	@RequestMapping("/delete/{index}")
	public String delete(@PathVariable("index") int index, Model model) {
		lottos.remove(index);
		model.addAttribute("lotto", null);
		model.addAttribute("stat", get());
		model.addAttribute("stat2", getMap());
		model.addAttribute("lottos", lottos);
		return "redirect:../";
	}

	// 隨機生成最新電腦選號
	private Set<Integer> getRandomLotto() {
		Set<Integer> lotto = new LinkedHashSet<>();
		Random r = new Random();
		// 樂透539 1~39 不重複取五
		// while 重複不會加入
		while (lotto.size() < 5) {
			lotto.add(r.nextInt(39) + 1);
		}
		return lotto;
	}

	@RequestMapping("/stat")
	public String group(Model model) {
		model.addAttribute("stat", get());
		model.addAttribute("stat2", getMap());
		model.addAttribute("lotto", null);
		model.addAttribute("lottos", lottos);
		return "session09/lotto";
	}

	private Map<Integer, Long> get() {
        
		// parallel() 多工緒(平行) 一個時間做多件事
		// 多執行緒(分時多工)
		// 將資料彙集 先將全部檔案拆散(flatMap)並用 collect 收集
		List<Integer> nums = lottos.stream().parallel().flatMap(lotto -> lotto.stream()) // stream()
				.collect(Collectors.toList()); // List<Integer>

		// 分組
		// 數 次數
		Map<Integer, Long> group = nums.stream().parallel()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		// 排序結果
		// 數 次數
		Map<Integer, Long> map = new LinkedHashMap<>();
		// 排序後放入結果map
		group.entrySet().stream().parallel().sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
				.forEachOrdered(e -> map.put(e.getKey(), e.getValue()));
		// forEachOrdered 按照串流順序 , reversed 倒敘
		System.out.println(map);
		return map;
	}

	private Map<Integer, Long> getMap() {

		// 將資料彙集 先將全部檔案拆散(flatMap)並用 collect 收集
		List<Integer> nums = lottos.stream().flatMap(lotto -> lotto.stream()) // stream()
				.collect(Collectors.toList()); // List<Integer>

		// 分組
		// 數 次數
		
		Map<Integer, Long> group = nums.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		Map<Integer, Long> mapkey = new LinkedHashMap<>();
		group.entrySet().stream().sorted(Map.Entry.<Integer, Long>comparingByKey())
				.forEachOrdered(e -> mapkey.put(e.getKey(), e.getValue()));
		
		return mapkey;
	}

}
