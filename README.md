<div align="center">
<h1>🖥️ CPU Scheduler</h1>

<p>
A menu-driven Java console application that simulates and compares classic
<strong>CPU scheduling algorithms</strong>: <strong>FCFS</strong>, <strong>SJF</strong>,
<strong>Round Robin</strong>, and <strong>Priority</strong> scheduling. Enter up to five processes,
run any algorithm, and review completion, waiting, and turnaround times together with the
average waiting and turnaround times.
</p>

<img src="https://img.shields.io/badge/Language-Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
<img src="https://img.shields.io/badge/Interface-Console-4D4D4D?style=for-the-badge&logo=gnubash&logoColor=white" alt="Console Application">
<img src="https://img.shields.io/badge/Topic-Operating_Systems-0078D4?style=for-the-badge" alt="Operating Systems">
<img src="https://img.shields.io/badge/Dependencies-None-green?style=for-the-badge" alt="No Dependencies">
</div>

<hr>

<h2>✨ Project Highlights</h2>
<ul>
<li><strong>Four Scheduling Algorithms:</strong> First Come First Served (FCFS), Shortest Job First (non-preemptive), Round Robin (with a user-defined time quantum), and Priority scheduling (non-preemptive).</li>
<li><strong>Algorithm Comparison:</strong> Pick any two algorithms and compare their average waiting time (AWT) and average turnaround time (ATAT) side by side.</li>
<li><strong>Input Validation:</strong> Every numeric input is validated, so invalid text, zero, or negative values are rejected and the user is asked again.</li>
<li><strong>Arrival Time Support:</strong> Handles processes that arrive at different times, including idle CPU gaps when no process is ready.</li>
<li><strong>Clean Structure:</strong> The scheduling logic is separated from the user interface in its own <code>scheduler</code> package.</li>
<li><strong>Zero Dependencies:</strong> Uses only the Java standard library.</li>
</ul>

<hr>

<h2>🧠 Supported Algorithms</h2>

<table>
<tr><th>Algorithm</th><th>Type</th><th>Selection Rule</th></tr>
<tr><td><strong>FCFS</strong></td><td>Non-preemptive</td><td>Processes run in order of arrival time.</td></tr>
<tr><td><strong>SJF</strong></td><td>Non-preemptive</td><td>Among the arrived processes, the one with the smallest burst time runs next.</td></tr>
<tr><td><strong>Round Robin</strong></td><td>Preemptive</td><td>Each process runs for at most one time quantum, then returns to the end of the ready queue.</td></tr>
<tr><td><strong>Priority</strong></td><td>Non-preemptive</td><td>Among the arrived processes, the one with the smallest priority number (highest priority) runs next.</td></tr>
</table>

<h3>📐 Metrics</h3>

<table>
<tr><th>Metric</th><th>Abbreviation</th><th>Formula</th></tr>
<tr><td>Completion Time</td><td>CT</td><td>Time at which the process finishes</td></tr>
<tr><td>Turnaround Time</td><td>TAT</td><td><code>CT − Arrival Time</code></td></tr>
<tr><td>Waiting Time</td><td>WT</td><td><code>TAT − Burst Time</code></td></tr>
<tr><td>Average Waiting Time</td><td>AWT</td><td>Sum of all WT ÷ number of processes</td></tr>
<tr><td>Average Turnaround Time</td><td>ATAT</td><td>Sum of all TAT ÷ number of processes</td></tr>
</table>

<hr>

<h2>🧾 Process Input Rules</h2>

<table>
<tr><th>Field</th><th>Rule</th></tr>
<tr><td>Number of processes</td><td>From 1 to 5</td></tr>
<tr><td>Burst Time</td><td>Integer greater than 0</td></tr>
<tr><td>Arrival Time</td><td>Integer greater than or equal to 0</td></tr>
<tr><td>Priority</td><td>Integer greater than 0 (a smaller number means a higher priority)</td></tr>
<tr><td>Time Quantum</td><td>Integer greater than 0 (Round Robin only)</td></tr>
</table>

<p>Process IDs are generated automatically as <code>P1</code>, <code>P2</code>, <code>P3</code>, and so on.</p>

<hr>

<h2>📋 Menu Options</h2>

<table>
<tr><th>Option</th><th>Action</th></tr>
<tr><td><code>1</code></td><td>Enter process information (replaces any existing processes and results)</td></tr>
<tr><td><code>2</code></td><td>Display the entered processes</td></tr>
<tr><td><code>3</code></td><td>Run a scheduling algorithm and print its results table and averages</td></tr>
<tr><td><code>4</code></td><td>Compare two scheduling algorithms by AWT and ATAT</td></tr>
<tr><td><code>5</code></td><td>Show the last results again</td></tr>
<tr><td><code>6</code></td><td>Reset all processes and results</td></tr>
<tr><td><code>7</code></td><td>Exit the program</td></tr>
</table>

<hr>

<h2>🗂️ Project Structure</h2>

<pre><code>CPU-Scheduler/
├── Main.java
├── README.md
└── scheduler/
    ├── CPUScheduler.java
    ├── Process.java
    └── ResultRow.java
</code></pre>

<table>
<tr><th>File</th><th>Responsibility</th></tr>
<tr><td><code>Main.java</code></td><td>Console menu, input reading and validation, and result printing.</td></tr>
<tr><td><code>scheduler/CPUScheduler.java</code></td><td>Implements the FCFS, SJF, Round Robin, and Priority algorithms.</td></tr>
<tr><td><code>scheduler/Process.java</code></td><td>Stores the PID, burst time, arrival time, and priority of one process.</td></tr>
<tr><td><code>scheduler/ResultRow.java</code></td><td>Stores the completion, turnaround, and waiting times of one process.</td></tr>
</table>

<hr>

<h2>⚙️ Prerequisites</h2>

<p>To compile and run the project, you will need:</p>
<ul>
<li><strong>Java Development Kit (JDK):</strong> Version 8 or higher</li>
<li><strong>Terminal or command prompt:</strong> Or any Java IDE such as IntelliJ IDEA, Eclipse, NetBeans, or VS Code</li>
</ul>

<p>Check your Java installation with:</p>

<pre><code>java -version
javac -version</code></pre>

<hr>

<h2>🚀 How to Run</h2>

<ol>
<li><strong>Clone the repository:</strong>

<pre><code>git clone https://github.com/&lt;your-username&gt;/&lt;your-repository&gt;.git</code></pre>
</li>

<li><strong>Navigate to the project directory:</strong>

<pre><code>cd &lt;your-repository&gt;</code></pre>
</li>

<li><strong>Compile the source files:</strong>

<pre><code>javac scheduler/*.java Main.java</code></pre>
</li>

<li><strong>Run the program:</strong>

<pre><code>java Main</code></pre>
</li>
</ol>

<p>
If you use an IDE, create a new Java project, keep <code>Main.java</code> in the default package, keep the
other three files inside a package folder named <code>scheduler</code>, and run <code>Main</code>.
</p>

<hr>

<h2>🧪 Example</h2>

<p>Sample input using three processes:</p>

<table>
<tr><th>PID</th><th>Arrival Time</th><th>Burst Time</th><th>Priority</th></tr>
<tr><td>P1</td><td>0</td><td>5</td><td>2</td></tr>
<tr><td>P2</td><td>1</td><td>3</td><td>1</td></tr>
<tr><td>P3</td><td>2</td><td>8</td><td>3</td></tr>
</table>

<p>Running <strong>FCFS</strong> (option <code>3</code> → algorithm <code>1</code>) produces:</p>

<pre><code>--- FCFS Scheduling Results ---
PID	AT	BT	CT	WT	TAT
P1	0	5	5	0	5
P2	1	3	8	4	7
P3	2	8	16	6	14
Average Waiting Time (AWT): 3.33 ms
Average Turnaround Time (ATAT): 8.67 ms
</code></pre>

<p>Comparing two algorithms (option <code>4</code>) prints a summary such as:</p>

<pre><code>========== ALGORITHM COMPARISON ==========
FCFS            AWT: 3.33     ATAT: 8.67
Round Robin     AWT: ...      ATAT: ...
==========================================
</code></pre>

<hr>

<h2>🔍 How It Works</h2>

<ul>
<li><strong>FCFS:</strong> Sorts a copy of the process list by arrival time and runs each process to completion, jumping the clock forward if the CPU would otherwise be idle.</li>
<li><strong>SJF and Priority:</strong> At each step, scan the processes that have already arrived and pick the best candidate by burst time or priority. If none have arrived, the clock advances by one time unit.</li>
<li><strong>Round Robin:</strong> Uses a queue of ready processes. Each turn runs for <code>min(quantum, remaining time)</code>, newly arrived processes are added to the queue, and unfinished processes go to the back of the queue.</li>
<li><strong>Results:</strong> Each algorithm returns a list of <code>ResultRow</code> objects, so the same printing and comparison code works for all of them. The original process list is never modified.</li>
</ul>

<hr>

<h2>⚠️ Notes and Limitations</h2>

<blockquote>
The program supports a maximum of <strong>5 processes</strong> per run.</li>
SJF and Priority are <strong>non-preemptive</strong>; preemptive versions (SRTF and preemptive priority) are not included.</li>
When two processes tie (same burst time or priority), the one that appears first in the input list is selected.</li>
Data is held in memory only and is cleared when the program exits.</li>
</blockquote>


<hr>

<p align="center">
<i>Developed by <strong>Abdulqader & Hamed</strong></i>
</p>
<p align="center">
<i>Built with Java.</i>
</p>
