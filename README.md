# Stable-Matching
Implemented a stable matching problem (which is an extended version of Gale-Shapley Algorithm) for Training and Placement purpoes.

We would like to develop some software for Training and Placement (T&P cell) at IIT Delhi for helping them to match IIT Delhi graduates to appropriate companies that come for placement. Every graduate G has a preference list of companies where the graduate ranks the company that he/she is interested in. Note that the preference list for a candidate G may include only a subset of companies that come for placement. Every company C that participates in the placement has an upper bound on the number of graduates that it can hire. Let us call this upper bound the capacity of the company. Every company uses its own metric to evaluate the candidates who are interested in the company (this may be based on spot tests, CGPA etc.). Based on its metric for evaluation, each company has a preference list for graduates that are interested in this company where it ranks the interested graduates. Let the companies be denoted by C1, ..., Cm and the graduates by denoted by G1, ..., Gn. Our software should reasonably match graduates to companies. In other words, it should assign graduates to companies such that certain constraints are satisfied. Let f : {G1, ..., Gn} → {C1, ..., Cm} ∪ {⊥} denote an assignment
function. That is, f(G10) = C2 iff graduate G10 has been assigned company C2. A graduate G is not assigned any company iff f(G) = ⊥.

• At most one job: Each graduate can be assigned at most one company. This constraint is automatically taken care of by using an assignment function for matching.

• Capacity constraint: Each company cannot hire more that its capacity (which it had declared before placement started).

• Stable matching: The assignment f should be stable. An assignment is stable iff there does not exist any blocking pairs. A company-graduate pair (C, G) is said to be blocking iff all the following conditions are satisfied:

1. G is interested in company C. That is, C appears in the preference list of graduate G.
2. f(G) = ⊥ OR G prefers C to f(G).
3. C’s capacity is not been met OR C prefers G to at least one graduate assigned to it.

The stable matching constraint makes sense because if a blocking pair (C, G) exists, then G may leave its currently assigned company and move to C. The problem we have described above is actually a popular problem known as Hospital Resident problem (HR problem in short). This is general version of the more popular Stable Matching problem. The HR problem may be solved using an extended version of the Gale-Shapley algorithm. You are requested to do research on these topics by yourself. There is a lot of material that is available on the web on these topics.

• Add a company: This adds a new participating company into the system. Note that all the participating companies should be added before performing any other instructions. So, if there are 10 participating companies, then the first 10 instructions should be to add these 10
companies. You may assume that at most 1000 companies will participate in the placement this year. Examples of this instruction is given below:

ADD COMPANY C1, Google, 5

ADD COMPANY C2, Amazon, 10

ADD COMPANY C3, Microsoft, 7

ADD COMPANY C4, Flipkart, 15

(Google is given companyID C1 and its hiring capacity is 5).

• Add a graduate: This adds a graduate to the system. These instructions should succeed the previous instructions (add a company) and precede all other instructions. You may assume that at most 5000 graduates will participate in placements this year. Examples of this instruction is given below:

ADD GRADUATE 2013CS10001, Aakash, 9.1, C1, C2, C3

ADD GRADUATE 2013CS10002, Aastha, 9.3, C2, C1, C3

(Aakash is a graduating student with entry no. 2013CS10001 and CGPA 9.1. His first preference
is Google followed by Amazon and Microsoft)

• Add company’s ranking of interested graduates: Every participating company uses their own metric to evaluate interested graduates and ranks them (in decreasing order of preference). This ranking should be entered into the system. Example of this instruction is given below:

RANK GRADUATES C1, 2013CS10001, 2013CS10002

RANK GRADUATES C2, 2013CS10002, 2013CS10001
(Google prefers Aakash to Aastha whereas Amazon prefers Aastha to Aakash)

• Display company information: This is self-explanatory. Here is an example:

DISPLAY COMPANY C2

This should produce the following output (on standard output):
Amazon, 10

• Display graduate information: This is self-explanatory. Here is an example:

DISPLAY GRADUATE 2013CS10001

This should produce the following output (on standard output):
Aakash, 9.1, C1, C2, C3

Here is an example file Query.txt:

ADD COMPANY C1, Google, 5

ADD COMPANY C2, Amazon, 10

ADD COMPANY C3, Microsoft, 7

ADD COMPANY C4, Flipkart, 15

ADD GRADUATE 2013CS10001, Aakash, 9.1, C1, C2, C3

ADD GRADUATE 2013CS10002, Aastha, 9.3, C2, C1, C3

ADD GRADUATE 2013CS10003, Akshay, 8.9, C4, C1

RANK GRADUATES C1, 2013CS10001, 2013CS10002, 2013CS10003

RANK GRADUATES C2, 2013CS10002, 2013CS10001

RANK GRADUATES C3, 2013CS10001, 2013CS10002

RANK GRADUATES C4, 2013CS10003

MATCH

UPDATE GRADUATE PREFERENCE 2013CS10003, C4

DELETE COMPANY C4

MATCH

For the above the query file the output (on standard output) should be the following:

2013CS10001, C1

2013CS10002, C2

2013CS10003, C4

2013CS10001, C1

2013CS10002, C2

2013CS10003,
