<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Random" %>

<html>
<head>
    <title>Addition Quiz</title>
</head>
<body>
    <h2>Addition Quiz</h2>

    <%
        int numQuestions = 10;
        Random rand = new Random();
        int[] num1 = (int[]) session.getAttribute("num1");
        int[] num2 = (int[]) session.getAttribute("num2");

        // If questions are not stored in session, generate new ones
        if (num1 == null || num2 == null) {
            num1 = new int[numQuestions];
            num2 = new int[numQuestions];

            for (int i = 0; i < numQuestions; i++) {
                num1[i] = rand.nextInt(20) + 1;
                num2[i] = rand.nextInt(20) + 1;
            }

            // Store questions in session
            session.setAttribute("num1", num1);
            session.setAttribute("num2", num2);
        }

        // Check if the form was submitted
        if (request.getParameter("submit") != null) {
            int correctCount = 0;

            for (int i = 0; i < numQuestions; i++) {
                int userAnswer = Integer.parseInt(request.getParameter("answer" + i));
                int correctAnswer = num1[i] + num2[i];

                if (userAnswer == correctAnswer) {
    %>
                    <p><%= num1[i] %> + <%= num2[i] %> = <%= userAnswer %> ✅ Correct</p>
    <%
                    correctCount++;
                } else {
    %>
                    <p><%= num1[i] %> + <%= num2[i] %> = <%= userAnswer %> ❌ Wrong (Correct answer: <%= correctAnswer %>)</p>
    <%
                }
            }
    %>
            <h3>Total Correct Answers: <%= correctCount %> out of <%= numQuestions %></h3>
    <%
            // Clear the session after displaying results
            session.removeAttribute("num1");
            session.removeAttribute("num2");
        } else {
    %>
        <form method="post">
            <%
                for (int i = 0; i < numQuestions; i++) {
            %>
                <label><%= num1[i] %> + <%= num2[i] %> = </label>
                <input type="number" name="answer<%= i %>" required><br><br>
            <%
                }
            %>
            <input type="submit" name="submit" value="Submit Answers">
        </form>
    <%
        }
    %>

</body>
</html>