<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>
<%@ page import="javax.servlet.ServletException" %>
<%@ page import="java.io.IOException" %>

<%! 
    public class Loan {
        private double annualInterestRate;
        private int numberOfYears;
        private double loanAmount;
        private Date loanDate;

        public Loan() {
            this(2.5, 1, 1000);
        }

        public Loan(double annualInterestRate, int numberOfYears, double loanAmount) {
            this.annualInterestRate = annualInterestRate;
            this.numberOfYears = numberOfYears;
            this.loanAmount = loanAmount;
            loanDate = new Date();
        }

        public double getMonthlyPayment() {
            double monthlyInterestRate = annualInterestRate / 1200;
            return loanAmount * monthlyInterestRate /
                   (1 - (1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12)));
        }

        public double getTotalPayment() {
            return getMonthlyPayment() * numberOfYears * 12;
        }
    }
%>

<html>
<head>
    <title>Loan Calculator</title>
</head>
<body>
    <h2>Loan Calculator</h2>

    <%
        String amountStr = request.getParameter("amount");
        String rateStr = request.getParameter("rate");
        String yearsStr = request.getParameter("years");

        if (amountStr != null && rateStr != null && yearsStr != null &&
            !amountStr.isEmpty() && !rateStr.isEmpty() && !yearsStr.isEmpty()) {

            double loanAmount = Double.parseDouble(amountStr);
            double annualInterestRate = Double.parseDouble(rateStr);
            int numberOfYears = Integer.parseInt(yearsStr);

            Loan loan = new Loan(annualInterestRate, numberOfYears, loanAmount);
            double monthlyPayment = loan.getMonthlyPayment();
            double totalPayment = loan.getTotalPayment();
    %>
        <h2>Loan Calculation Result</h2>
        <p>Loan Amount: <%= loanAmount %></p>
        <p>Annual Interest Rate: <%= annualInterestRate %>%</p>
        <p>Number of Years: <%= numberOfYears %></p>
        <p>Monthly Payment: <%= monthlyPayment %></p>
        <p>Total Payment: <%= totalPayment %></p>
    <%
        } else {
    %>
        <form method="post">
            <label for="amount">Loan Amount:</label>
            <input type="number" id="amount" name="amount" required><br><br>

            <label for="rate">Annual Interest Rate:</label>
            <input type="number" step="0.01" id="rate" name="rate" required><br><br>

            <label for="years">Number of Years:</label>
            <input type="number" id="years" name="years" required><br><br>

            <input type="submit" value="Compute Loan Payment">
        </form>
    <%
        }
    %>
</body>
</html>
