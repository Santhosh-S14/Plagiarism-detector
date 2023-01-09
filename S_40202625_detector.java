import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class S_40202625_detector {

	static int shortest_len_code;
	static int shortest_file_len_s;
	static ArrayList<String> file_1_contents = new ArrayList<String>();
	static ArrayList<String> file_2_contents = new ArrayList<String>();
	static ArrayList<String> to_find_code = new ArrayList<String>(
			Arrays.asList("iostream", "#include", "int ", "def ", "elif", "//", "// ", "# ", "/* ", "*/", "/** "));

	public static void main(String args[]) {
		file_1_contents = readFile(args[0]);
		file_2_contents = readFile(args[1]);
		shortest_file_len_s = Math.min(file_1_contents.toString().split(" ").length,
				file_2_contents.toString().split(" ").length);
		shortest_len_code = Math.min(file_1_contents.toString().split(" ").length,
				file_2_contents.toString().split(" ").length);
		boolean text_or_code = checkType(file_1_contents);
		if (!text_or_code) {
			textProcessing();
		} else {
			codeProcessing();
		}
	}

	private static ArrayList<String> readFile(String file_argument) {
		// TODO Auto-generated method stub
		ArrayList<String> contents = new ArrayList<>();
		File f;
		f = new File(file_argument);
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line;
			try {
				while ((line = br.readLine()) != null) {
					contents.add(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contents;
	}

	private static boolean checkType(ArrayList<String> contents) {
		// TODO Auto-generated method stub
		for (String c : contents) {
			String[] words = c.split(" ");
			for (String word : words) {
				if (to_find_code.contains(word)) {
					return true;
				}
			}
		}
		return false;
	}

	private static void textProcessing() {
		// TODO Auto-generated method stub
		int small_file_threshold = 0;
		int large_file_threshold = 0;
		if (file_1_contents.toString().length() <= 500 || file_2_contents.toString().length() <= 500) {
			int len_small_files = lcs(file_1_contents.toString().replaceAll("[^a-zA-Z0-9\\s]", ""),
					file_2_contents.toString().replaceAll("[^a-zA-Z0-9\\s]", ""));
			small_file_threshold = 100 * len_small_files / Math.min(file_1_contents.toString().split(" ").length,
					file_2_contents.toString().split(" ").length);
		} 
		String f1_c[] = file_1_contents.toString().split("[.] ");
		String f2_c[] = file_2_contents.toString().split("[.] ");
		int final_value = 0;
		int f1_words = 0;
		boolean check = false;
		boolean exiting_early = false;
		for (String f1c : f1_c) {
			int max_len_lcs = Integer.MIN_VALUE;
			f1_words += f1c.split(" ").length;
			f1c = f1c.replaceAll("[^a-zA-Z0-9\\s]", "");
			for (String f2c : f2_c) {
				f2c = f2c.replaceAll("[^a-zA-Z0-9\\s]", "");
				int len = lcs(f1c, f2c);
				if (len > max_len_lcs) {
					max_len_lcs = len;
				}
			}
			final_value += max_len_lcs;
			int value = 100 * final_value / f1_words;
			if (value > 50 && f1_words >= 100) {
				check = true;
				exiting_early = true;
				break;
			}
		}
		if (check && exiting_early) {
			System.out.print(1);
		} else {
			large_file_threshold = 100 * final_value / shortest_len_code;
			if (Math.max(small_file_threshold, large_file_threshold) > 50) {
				check = true;
			} else {
				check = false;
			}
			if (check) {
				System.out.print(1);
			} else {
				System.out.print(0);
			}
		}

	}

	private static void codeProcessing() {
		// TODO Auto-generated method stub
		int final_value = 0;
		int f1_words = 0;
		boolean check = false;
		boolean exiting_early = false;
		for (String f1c : file_1_contents) {
			int max_len_lcs = Integer.MIN_VALUE;
			f1_words += f1c.split(" ").length;
			for (String f2c : file_2_contents) {
				int len = lcs(f1c, f2c);
				if (len > max_len_lcs) {
					max_len_lcs = len;
				}
			}
			final_value += max_len_lcs;
			int value = 100 * final_value / f1_words;
			if (value > 50 && f1_words >= 100) {
				check = true;
				exiting_early = true;
				break;
			}
		}
		if (check && exiting_early) {
			System.out.print(1);
		} else {
			int threshold = 100 * final_value / shortest_len_code;
			System.out.println(threshold);
			if (threshold > 50) {
				check = true;
			} else {
				check = false;
			}
			if (check) {
				System.out.print(1);
			} else {
				System.out.print(0);
			}
		}

	}

	private static int lcs(String file_1_contents, String file_2_contents) {
		// TODO Auto-generated method stub
		String[] left_contents = file_1_contents.split(" ");
		String[] right_contents = file_2_contents.split(" ");
		int file_1_length = left_contents.length;
		int file_2_length = right_contents.length;
		int D[][] = new int[file_1_length + 1][file_2_length + 1];
		for (int i = 0; i <= file_1_length; i++) {
			for (int j = 0; j <= file_2_length; j++) {
				if (i == 0 || j == 0) {
					D[i][j] = 0;
				} else if (left_contents[i - 1].equals(right_contents[j - 1])) {
					D[i][j] = 1 + D[i - 1][j - 1];
				} else {
					D[i][j] = Math.max(D[i - 1][j], D[i][j - 1]);
				}
			}
		}
		return D[file_1_length][file_2_length];

	}

}
