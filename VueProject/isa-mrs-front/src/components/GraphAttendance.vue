<template>
    <div>
        <div>
            <h5><center> Weekly attendance</center> </h5>
            <canvas id="weekly" width="400" height="200" style="border:1px solid #000000;"></canvas>
        </div>
        <br>
        <div>
            <h5><center> Monthly attendance</center> </h5>
            <canvas id="monthly" width="400" height="200" style="border:1px solid #000000;"></canvas>
        </div>
        <br>
        <div>
            <h5><center> Yearly attendance</center> </h5>
            <canvas id="yearly" width="400" height="200" style="border:1px solid #000000;"></canvas>
        </div>
    </div>
</template>
<script>

import Chart from 'chart.js/auto'

export default {
    data(){
        return {
            selected: "",
            weekly: false,
            monthly: false,
            yearly: false
        }
    },
    methods: {
        showGraph() {
            if (this.selected === 'weekly') {
                this.weekly = true;
                this.monthly = false;
                this.yearly = false;
            }else if (this.selected === 'monthly') {
                this.weekly = false;
                this.monthly = true;
                this.yearly = false;
            }else if (this.selected === 'yearly') {
                this.weekly = false;
                this.monthly = false;
                this.yearly = true;
            }
        }
    },
    mounted(){







        const ctxWeekly = document.getElementById('weekly');
        const ctxMonthly = document.getElementById('monthly');
        const ctxYearly = document.getElementById('yearly');

        const weeklyChart = new Chart(ctxWeekly, {
            type: 'bar',
            data: {
                labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
                datasets: [{
                    label: '# of Votes',
                    data: [12, 19, 3, 5, 2, 3],
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
        const monthlyChart = new Chart(ctxMonthly, {
            type: 'bar',
            data: {
                labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
                datasets: [{
                    label: '# of Votes',
                    data: [12, 19, 3, 5, 2, 3],
                    backgroundColor: [
                        'rgba(255, 99, 132, 2)',
                        'rgba(54, 162, 235, 2)',
                        'rgba(255, 206, 86, 2)',
                        'rgba(75, 192, 192, 2)',
                        'rgba(153, 102, 255, 2)',
                        'rgba(255, 159, 64, 2)'
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
        const yearlyChart = new Chart(ctxYearly, {
            type: 'bar',
            data: {
                labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
                datasets: [{
                    label: '# of Votes',
                    data: [12, 19, 3, 5, 2, 3],
                    backgroundColor: [
                        'rgba(255, 99, 132, 2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
        weeklyChart;
        monthlyChart;
        yearlyChart;
    }
}
</script>
