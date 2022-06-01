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
import axios from 'axios'
import VueAxios from 'vue-axios'
import Vue from 'vue'
Vue.use(VueAxios, axios)

export default {
    data(){
        return {
            selected: "",
            weekly: false,
            monthly: false,
            yearly: false,
            yearlyLabels: [],
            yearlyValues: [],
            monthlyLabels: [],
            monthlyValues: [],
            weeklyLabels: [],
            weeklyValues: []
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
        axios.get("http://localhost:8088/reservations/yearlyReport", {
            headers: {
                Authorization: 'Bearer ' + window.sessionStorage.getItem('accessToken')               
            }
        }).then((response) =>{
            this.yearlyLabels = response.data.dates;
            this.yearlyValues = response.data.values;
            const ctxYearly = document.getElementById('yearly');
            const yearlyChart = new Chart(ctxYearly, {
                type: 'bar',
                data: {
                    labels: this.yearlyLabels,
                    datasets: [{
                        label: 'Reservations per year',
                        data: this.yearlyValues,
                        backgroundColor: [
                            'rgba(242, 145, 120, 2)',
                            'rgba(255, 0, 0, 2)',
                            'rgba(0, 0, 255, 2)',
                            'rgba(60, 179, 113, 2)',
                            'rgba(238, 130, 238, 2)',
                            'rgba(255, 165, 0, 2)',
                            'rgba(227, 255, 71, 2)',
                            'rgba(227, 59, 255, 2)'
                        ],
                        borderColor: [
                            'rgba(242, 145, 120, 1)',
                            'rgba(255, 0, 0, 1)',
                            'rgba(0, 0, 255, 1)',
                            'rgba(60, 179, 113, 1)',
                            'rgba(238, 130, 238, 1)',
                            'rgba(255, 165, 0, 1)',
                            'rgba(227, 255, 71, 1)',
                            'rgba(227, 59, 255, 1)'
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
            yearlyChart;
        }),
        axios.get("http://localhost:8088/reservations/monthlyReport",{
            headers:{
                Authorization: 'Bearer ' + window.sessionStorage.getItem('accessToken')
            }
        }).then((response) => {
            this.monthlyLabels = response.data.dates;
            this.monthlyValues = response.data.values;
            const ctxMonthly = document.getElementById('monthly');
            const monthlyChart = new Chart(ctxMonthly, {
                type: 'bar',
                data: {
                    labels: this.monthlyLabels,
                    datasets: [{
                        label: 'Reservations per month',
                        data: this.monthlyValues,
                        backgroundColor: [
                            'rgba(242, 145, 120, 2)',
                            'rgba(255, 0, 0, 2)',
                            'rgba(0, 0, 255, 2)',
                            'rgba(60, 179, 113, 2)',
                            'rgba(238, 130, 238, 2)',
                            'rgba(255, 165, 0, 2)',
                            'rgba(227, 255, 71, 2)',
                            'rgba(227, 59, 255, 2)'
                        ],
                        borderColor: [
                            'rgba(255, 99, 71, 1)',
                            'rgba(255, 0, 0, 1)',
                            'rgba(0, 0, 255, 1)',
                            'rgba(60, 179, 113, 1)',
                            'rgba(238, 130, 238, 1)',
                            'rgba(255, 165, 0, 1)',
                            'rgba(227, 255, 71, 1)',
                            'rgba(227, 59, 255, 1)'
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
            monthlyChart;
        }),
        axios.get("http://localhost:8088/reservations/weeklyReport",{
            headers:{
                Authorization: 'Bearer ' + window.sessionStorage.getItem('accessToken')
            }
        }).then((response) => {
            this.weeklyLabels = response.data.dates;
            this.weeklyValues = response.data.values;
            const ctxWeekly = document.getElementById('weekly');
            const weeklyChart = new Chart(ctxWeekly, {
                type: 'bar',
                data: {
                    labels: this.weeklyLabels,
                    datasets: [{
                        label: 'Reservations per week',
                        data: this.weeklyValues,
                        backgroundColor: [
                            'rgba(242, 145, 120, 2)',
                            'rgba(255, 0, 0, 2)',
                            'rgba(0, 0, 255, 2)',
                            'rgba(60, 179, 113, 2)',
                            'rgba(238, 130, 238, 2)',
                            'rgba(255, 165, 0, 2)',
                            'rgba(227, 255, 71, 2)',
                            'rgba(227, 59, 255, 2)'
                        ],
                        borderColor: [
                            'rgba(242, 145, 120, 1)',
                            'rgba(255, 0, 0, 1)',
                            'rgba(0, 0, 255, 1)',
                            'rgba(60, 179, 113, 1)',
                            'rgba(238, 130, 238, 1)',
                            'rgba(255, 165, 0, 1)',
                            'rgba(227, 255, 71, 1)',
                            'rgba(227, 59, 255, 1)'
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
        })       
    }
}
</script>
