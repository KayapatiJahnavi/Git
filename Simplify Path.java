class Solution {
    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        String[] parts = path.split("/");

        for (String part : parts) {
            if (part.equals("") || part.equals(".")) {
                continue; // Skip empty and current directory
            } else if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop(); // Go back to previous directory
                }
            } else {
                stack.push(part); // Valid directory name
            }
        }

        // Reconstruct the path
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.insert(0, "/" + stack.pop());
        }

        return result.length() == 0 ? "/" : result.toString();
    }
}

