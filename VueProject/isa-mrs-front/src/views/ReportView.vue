<template>
    <div>
        <NavbarUser></NavbarUser>
        <div class="d-flex flex-column justify-content-center mx-auto align-items-center my-4">
            <h2 style="margin: 0px 10px 0px 0px"> Business reports </h2>
            <div class="d-flex flex-row" style="margin: 50px">
                <label style="margin:10px;">Choose report type: </label>
                <select v-model="selected" name="reportType">
                    <option value="averageRating">Average service rating</option>
                    <option value="graphAttendance">Service graph of attendance</option>
                    <option value="incomeReport">Income report</option>
                </select>
                <button class="btn btn-primary" @click="showReport" style="margin: 10px">Show report</button>
            </div>
            <div>
                <div v-if="averageRating">
                    <AverageServiceRating></AverageServiceRating>
                </div>
				<div v-if="incomeReport">
                    <IncomeReportPrivilegedUser></IncomeReportPrivilegedUser>
                </div>
                <div v-if="graphAttendance">
                    <GraphAttendance></GraphAttendance>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    import IncomeReportPrivilegedUser from '@/components/IncomeReportPrivilegedUser.vue'
    import NavbarUser from '@/components/NavbarUser.vue'
    import AverageServiceRating from '@/components/AverageServiceRating.vue'
    import GraphAttendance from '@/components/GraphAttendance.vue'
    import router from '@/router'

    export default {
        name: 'ReportView',
        components: {
            NavbarUser,
            AverageServiceRating,
            IncomeReportPrivilegedUser,
            GraphAttendance
        },
        data() {
            return {
                selected: "",
                averageRating: false,
                graphAttendance: false,
                incomeReport: false
            }
        },
        methods: {
            showReport() {
                if (this.selected === 'averageRating') {
                    this.incomeReport = false;
                    this.graphAttendance = false;
                    this.averageRating = true;
                }else if (this.selected === 'graphAttendance') {
                    this.averageRating = false;
                    this.incomeReport = false;
                    this.graphAttendance = true;
                }else if (this.selected === 'incomeReport') {
                    this.averageRating = false;
                    this.graphAttendance = false;
                    this.incomeReport = true;
                }
            }
        },
        mounted() {
            if (window.sessionStorage.getItem('role') != "ROLE_retreatOwner" && window.sessionStorage.getItem("role") != "ROLE_shipOwner" && window.sessionStorage.getItem("role") != "ROLE_fishingInstructor") {
                router.push("/");
            }
        }
    }
</script>
