# GitHub Copilot Instructions for LeetCode Blind75 Solutions

## Project Overview
This repository contains Java solutions for the LeetCode Blind75 list, organized by algorithmic topic. Each solution file implements a single LeetCode problem with detailed analysis.

## File Structure & Naming
- **Location**: Solutions are grouped in topic folders (e.g., `Arrays_and_hashing/`, `Binary_search/`)
- **Naming**: `{PROBLEM_NUMBER}_{PROBLEM_NAME}.java` (e.g., `1_Two_Sum.java`, `704_Binary_search.java`)
- **Class Naming**: Use `Solution` for most problems; use descriptive names like `Codec` for design problems

## Code Patterns
### Solution Structure
Each file contains a single public class with the solution method. Include comprehensive comments explaining:
- Problem constraints and examples
- Approach with key insights
- Time/space complexity analysis
- Alternative solutions with trade-offs

**Example from `Arrays_and_hashing/1_Two_Sum.java`:**
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
```

### Comment Style
- Use Javadoc-style comments for problem description
- Include `APPROACH`, `PATTERN`, `KEY INSIGHT` sections
- Document complexity with examples (e.g., "1000000 elements takes ~20 comparisons")
- Reference related problems and citations

## Development Workflow
- **No build system**: Individual `.java` files, no `pom.xml` or `build.gradle`
- **Compilation**: Use `javac {filename}.java` to compile
- **Execution**: Solutions lack `main` methods; test via LeetCode platform or custom test harnesses
- **IDE**: IntelliJ IDEA project (`.idea/` directory present)

## Key Conventions
- **Imports**: Only import necessary classes (e.g., `java.util.*` for collections)
- **Data Structures**: Prefer standard library (HashMap, ArrayList, etc.)
- **Edge Cases**: Always handle empty inputs, single elements, maximum constraints
- **Multiple Solutions**: Include alternative approaches in same file with complexity comparison

## Reference Files
- `Arrays_and_hashing/1_Two_Sum.java`: Exemplifies hash map pattern and detailed commenting
- `Binary_search/704_Binary_search.java`: Shows recursive vs iterative implementations
- `graphs/Solution.java`: Demonstrates graph algorithms with adjacency lists
