# Plagiarism-detector

## Documents in the folder:

	1. S_40202625_detector.java
	2. README.txt
	3. Makefile

## How to run the make file:

	make FILE1=Filename1.txt FILE2=Filename2.txt run

## Description of the algorithm:

	Algorithm used to detect plagiarism: Longest Common Subsequence. 
	
	The flow of the program is as follows, 
	
		1. File is identified as a text file or a code file. 
		2. If a text file is found,
			1. Both the text files are split into sentences. 
			2. Each sentence is preprocessed by removing all the special characters. 
			3. Each sentence from the first file is compared with each sentence of the second file. 
			4. LCS is done on words from each sentences. 
			5. Maximum longest common sequence value is found for each sentence and added to a global sum. 
			6. Early exit strategy is used to tag a file as plagiarised by checking for threshold value after crossing a 100 words. This is done because if a file has been plagiarised in the first few sentences they are tagged as plagiarism ultimately at the end, so to reduce the run time this strategy was used.
			7. Finally to find a threshold value, the sum is divided over the number of words in the shortest file among the two files. 
		3. If a code file is found, 
			1. The file is split with "\n". 
			2. No special characters are removed from the file. 
			3. Rest of all the calculations are same as done for texts. 
			4. LCS is performed on each line of code with the second file and max LCS value is added to a global sum. 
			5. Threshold is, sum divided over the number of words in the shortest file among the two files. 
		
		## Identifying code files
		1. The file is scanned line by line and a global list contains a few words that are used in coding. 
		2. If they are identified, they are tagged as code files.  

		## Threshold set after running LCS on the test files given is: 50%. If larger it is detected as a plagiarised file and 1 is returned. Otherwise, 0 is returned. 

		## LCS gave very reliable result when done word wise for each sentence of the file. But since the algorithm gave reliable results, algorithm was not changed and optimized as much as possible. A tradeoff for LCS. 

		## Also if the file contains less than a length of 500 it is not split into sentences and instead considered as a full string instead. This also improves the run time and space because we create only one DP array in this case.
