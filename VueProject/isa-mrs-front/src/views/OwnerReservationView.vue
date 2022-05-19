<template>
    <div class="signUp">    
        <div class="app">
            <div class="vertical-center" style="margin-top: 85px">
                <div class="inner-block">
                    <form @submit.prevent style="padding:10px;padding-top:100px">
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
                            <input v-model="form.numberOfDays" id="name" type="number" min="1" class="form-control form-control-lg"/>
                        </div>
                        <div class="form-group required">
                            <label class="control-label">Number of people </label>
                            <input v-model="form.numberOfPeople" id="name" type="number" min="1" class="form-control form-control-lg"/>
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
                                <button @click="makeReservation"  :disabled="isFormValid == false" type="submit" class="btn btn-dark btn-lg btn-block">Make reservation</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
//    import Vuelidate from 'vuelidate'
    import Vue from 'vue'
    import axios from 'axios'
//    import router from '@/router'
    import VueAxios from 'vue-axios'

    Vue.use(VueAxios, axios)

    export default {
        name: "OwnerReserve",
        data() {
            return {
                form: {
                    date: null,
                    startTime: null,
                    endTime: null,
                    clientEmail: null,
                    numberOfDays: null,
                    numberOfPeople: null,
                    additionalServices: []
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
            isFormValid() {
                return true;
            }
        },
        methods: {
            makeReservation() {

            }
        },
        mounted() {
            let path = "";
            if (window.sessionStorage.getItem("role") === "ROLE_retreatOwner") path = 'retreats';
            else if (window.sessionStorage.getItem("role") === "ROLE_shipOwner") path = 'ships';
            else if (window.sessionStorage.getItem("role") === "ROLE_fishingInstructor") path = 'adventures';
            axios.get('http://localhost:8088/' + path + '/get/' + this.$route.params.id, {
                headers: {
                    Authorization: 'Bearer ' + window.sessionStorage.getItem('accessToken')
                }
            }).then((response) => {
                this.tags = response.data.additionalServices;
            });
        }
    }

</script>
