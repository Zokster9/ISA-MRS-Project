<template>
    <div v-if="reservations">
        <NavbarClient></NavbarClient>
        <div v-if="reservations.length > 0" class="complaints">    
            <div class="app">
                <div class="vertical-center" style="margin-top: 85px">
                    <div class="inner-block">
                        <div class="complaints">
                            <form @submit.prevent>
                                <div class="form-group required">
                                    <label class="control-label">Choose a reservation: </label>
                                    <select v-model="selected" class="form-select" aria-label="Reservations">
                                        <option disabled value="">Please Select</option>
                                        <option v-for="reservation in reservations" :value="reservation.id" :key="reservation.id">
                                            {{ reservation.serviceName }}: {{ getDate(reservation.fromDate) }} - {{ getDate(reservation.toDate) }}
                                        </option>
                                    </select>
                                </div>
                                <div class="form-group required">
                                    <label class="control-label">Write your complaint: </label>
                                    <textarea v-model="complaint" placeholder="Please enter your complaint..." class="form-control form-control-lg"></textarea>
                                </div>
                                <div class="form-group">
                                    <button @click="complain" :disabled="$v.$invalid" type="submit" class="btn btn-dark btn-lg btn-block">Send complaint</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div v-else class="complaints">    
            <div class="app">
                <div class="vertical-center" style="margin-top: 85px">
                    <div class="inner-block">
                        <div class="complaints">
                            <h3>You have no available reservations for complaints</h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import NavbarClient from '@/components/NavbarClient.vue'
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    import Vuelidate from 'vuelidate'
    import { required } from 'vuelidate/lib/validators'
import router from '@/router'
    Vue.use(VueAxios, axios)
    Vue.use(Vuelidate)

    export default {
        name: 'ClientComplaints',
        components: {
            NavbarClient,
        },
        data () {
            return {
                reservations: null,
                selected: '',
                complaint: '',
            }
        },
        methods: {
            complain() {
                axios.post("http://localhost:8088/complaints/makeAComplaint", {
                    reservationId: this.selected,
                    complaint: this.complaint,
                },
                {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem('accessToken')
                    }
                })
                .then(() => {
                    alert("Complaint successfully sent!");
                    this.reservations = this.reservations.filter(reservation => reservation.id != this.selected)
                    this.selected = ''
                    this.complaint = ''
                })
                .catch(() => {
                    alert("Complaint couldn't be sent!");
                })
            },
            getDate (date) {
                let origin_date = new Date(date)
                let month = origin_date.getMonth() + 1
                let day = origin_date.getDate()
                if (month < 10) {
                    month = '0' + month
                }
                if (day < 10) {
                    day = '0' + day
                }
                return origin_date.getFullYear() + '/' + month + '/' + day
            },
        },
        validations: {
            complaint: {
                required
            },
            selected: {
                required
            }
        },
        mounted () {
            if (window.sessionStorage.getItem("role") === "ROLE_client") {
                axios.get("http://localhost:8088/reservations/getNonComplainedReservations", {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem('accessToken')
                    }
                })
                .then(response => {
                    this.reservations = response.data;
                })
                .catch(() => {
                    alert("Something went wrong");
                })
            }
            else {
                router.push("/");
            }
        }
    }
</script>