<template>
    <div>
    <NavbarUser></NavbarUser>
    <div class="signUp">    
        <div class="app">
            <div class="vertical-center" style="margin-top: 85px">
                <div class="inner-block">
                    <form @submit.prevent style="padding:10px">
                        <h3>Make reservation for client:</h3> 
                        <div class="form-group required">
                            <label class="control-label">Starting date </label>
                            <input v-model="form.date" id="date" type="date" class="form-control form-control-lg"/>
                        </div>
                        <div class="form-group required">
                            <table>
                                <tr>
                                    <td>
                                        <label class="control-label">Starting time </label>
                                        <input v-model="form.startTime" id="time" type="time" class="form-control form-control-lg"/>
                                    </td>
                                    <td>
                                        <label class="control-label">Ending time </label>
                                        <input v-model="form.endTime" id="time" type="time" class="form-control form-control-lg"/>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="form-group required">
                            <label class="control-label">Duration of the reservation (in days) </label>
                            <input v-model.number="form.numberOfDays" id="name" type="number" min="1" class="form-control form-control-lg"/>
                        </div>
                        <div class="form-group required">
                            <label class="control-label">Number of people </label>
                            <input v-model.number="form.numberOfPeople" id="name" type="number" min="1" class="form-control form-control-lg"/>
                        </div>
                        <div class="form-group required">
                            <label class="control-label">Client email </label>
                            <input v-model="form.clientEmail" id="name" type="email" class="form-control form-control-lg"/>
                        </div>
                        <div class="form-group mb-3">
                            <label class="control-label">Additional services:</label>
                            <br>
                            <template v-for="tag in this.tags">
                                <div class="form-group mb-3" :key="tag">
                                    <input type="checkbox" v-model="form.additionalServices" :value="tag"/>
                                    <label :for="tag">{{tag}}</label>
                                </div>
                            </template>
                        </div>
                        <div class="form-group">
                                <button @click="makeReservation" :disabled="$v.form.$invalid || isFormValid == false" type="submit" class="btn btn-dark btn-lg btn-block">Make reservation</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    </div>
</template>

<script>
    import Vue from 'vue'
    import Vuelidate from 'vuelidate'
    import axios from 'axios'
    import {required, email} from 'vuelidate/lib/validators'
    import VueAxios from 'vue-axios'
    import router from '@/router'
    import NavbarUser from '@/components/NavbarUser.vue'

    Vue.use(VueAxios, axios)
    Vue.use(Vuelidate)

    export default {
        name: "OwnerReserve",
        components: {
            NavbarUser
        },
        data() {
            return {
                form: {
                    date: null,
                    startTime: null,
                    endTime: null,
                    clientEmail: null,
                    numberOfDays: null,
                    numberOfPeople: null,
                    additionalServices: [],
                    serviceName: null
                },
                tags: []
            }
        },
        computed: {
            maxNumOfPeopleIsValid(){
                return typeof this.form.numberOfPeople === 'number' && !!this.form.numberOfPeople && this.form.numberOfPeople > 0;
            },
            numberOfDaysIsValid(){
                return typeof this.form.numberOfDays === 'number' && !!this.form.numberOfDays && this.form.numberOfDays > 0;
            },
            areDatesValid() {
                let today = new Date();
                if (!this.form.date || !this.form.startTime || !this.form.endTime) return false;
                let startDate = new Date(this.form.date);
                let endDate = new Date(this.form.date);
                endDate.setDate(endDate.getDate() + parseInt(this.form.numberOfDays));
                if (startDate.getTime() < today.getTime()) return false;
                if (endDate.getTime() < today.getTime()) return false;
                if (startDate.getTime() > endDate.getTime()){
                    return false;
                }
                else if (startDate.getTime() < endDate.getTime()){
                    return true;
                }
                else{
                    if (parseInt(this.form.startTime.split(":")[0]) * 60 + parseInt(this.form.startTime.split(":")[1]) <  parseInt(this.form.endTime.split(":")[0]) * 60 + parseInt(this.form.endTime.split(":")[1])){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            },
            isFormValid() {
                return this.numberOfDaysIsValid && this.maxNumOfPeopleIsValid && this.areDatesValid;
            }
        },
        methods: {
            makeReservation() {
                if (this.isFormValid) {
                    let endDate = new Date(this.form.date);
                    endDate.setDate(endDate.getDate() + parseInt(this.form.numberOfDays));
                    axios.post('http://localhost:8088/reservations/make-for-client/' + this.$route.params.id, {
                        fromDate: this.form.date,
                        toDate: endDate,
                        fromTime: this.form.startTime,
                        toTime: this.form.endTime,
                        clientEmail: this.form.clientEmail,
                        numOfDays: this.form.numberOfDays,
                        numOfPeople: this.form.numberOfPeople,
                        additionalServices: this.form.additionalServices,
                        serviceName: this.form.serviceName
                    }, {
                        headers: {
                            Authorization: 'Bearer ' + window.sessionStorage.getItem('accessToken')
                        }
                    }).then(() => {
                        alert("Reservation added successfully!");
                    }).catch((error) => {
                        if (error.response.status === 409) alert("Reservations for this period already exists!");
                        else if (error.response.status === 403) alert("There is no current reservation for this client!");
                        else if (error.response.status === 406) alert("Your service is not available at specificated period!");
                        else alert("Could not add reservation");
                    });
                }
            }
        },
        mounted() {
            let path = "";
            if (window.sessionStorage.getItem("role") === "ROLE_retreatOwner") {
                path = 'retreats';
                this.form.serviceName = 'retreat';
            }
            else if (window.sessionStorage.getItem("role") === "ROLE_shipOwner") { 
                path = 'ships';
                this.form.serviceName = 'ship';
            }
            else if (window.sessionStorage.getItem("role") === "ROLE_fishingInstructor") {
                path = 'adventures';
                this.form.serviceName = 'adventure';
            }
            else {
                router.push("/");
            }
            axios.get('http://localhost:8088/' + path + '/get/' + this.$route.params.id, {
                headers: {
                    Authorization: 'Bearer ' + window.sessionStorage.getItem('accessToken')
                }
            }).then((response) => {
                this.tags = response.data.additionalServices;
            });
        },
        validations: {
            form: {
                clientEmail: {
                    required,
                    email
                }
            }
        }
    }

</script>
