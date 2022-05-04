#TODO: mozda dodati i sliku servisa u tabelu (pokupljeno je sa back-a)
<template>
    <tr :key="service.id">
        <td class="align-middle"> {{service.name}} </td>
        <td class="align-middle"> Starting date and time: </td>
        <td class="align-middle"> <input type="date" v-model="startDate" placeholder="Start date"></td>
        <td class="align-middle"> <input type="time" v-model="startTime" placeholder="Start time"></td>
        <td class="align-middle"> Ending date and time: </td>
        <td class="align-middle"> <input type="date" v-model="endDate" placeholder="End date"></td>
        <td class="align-middle"> <input type="time" v-model="endTime" placeholder="End time"></td>
        <td class="align-middle"> <button :disabled="!isValid" type="button" class="btn btn-success" @click="addAvailability(service.id)">Confirm</button></td>
    </tr>
</template>

<script>
    import Vue from 'vue'
    import Vuelidate from 'vuelidate'  
    import axios from 'axios'
    import VueAxios from 'vue-axios'

    Vue.use(VueAxios, axios)
    Vue.use(Vuelidate)

    export default {
        props: ["service"],
        data() {
            return {
                startDate: "",
                startTime: "",
                endDate: "",
                endTime: "",
            }
        },
        //TODO: BACK POZIV OVDE
        methods: {
            addAvailability(id){
                if (window.localStorage.getItem("role") === "ROLE_retreatOwner") {
                    this.addRetreatAvailability(id);
				}else if (window.localStorage.getItem("role") === "ROLE_shipOwner") {
                    this.addShipAvailability(id);
				}else if (window.localStorage.getItem("role") === "ROLE_fishingInstructor") {
                    // TODO: dodati za avanturu
				}else {
                    alert('Some kind of error happened!');
                }
            },
            addRetreatAvailability(id) {
                axios.post('http://localhost:8088/retreats/add-availability/' + id, {
                    dateFrom: new Date(this.startDate),
                    dateTo: new Date(this.endDate),
                    timeFrom: this.startTime,
                    timeTo: this.endTime
                }, {
                    headers: {
                        Authorization: 'Bearer ' + window.localStorage.getItem('accessToken')
                    }
                }).then(() => {
                    alert('Service availability added!');
                })
            },
            addShipAvailability(id) {
                axios.post('http://localhost:8088/ships/add-availability/' + id, {
                    dateFrom: new Date(this.startDate),
                    dateTo: new Date(this.endDate),
                    timeFrom: this.startTime,
                    timeTo: this.endTime
                }, {
                    headers: {
                        Authorization: 'Bearer ' + window.localStorage.getItem('accessToken')
                    }
                }).then(() => {
                    alert('Service availability added!');
                })
            }
        },
        computed: {
            isTimeValid() {
                let reg = new RegExp("[0-9]{2}':'[0-9]{2}");
                if (!reg.test(this.startTime)) return false;
                if (!reg.test(this.endTime)) return false; 
                return true;
            },
            isValid(){
                if (!this.startDate || !this.startTime || !this.endDate || !this.endTime){
                    return false;
                }
                if (new Date(this.startDate).getTime() > new Date(this.endDate).getTime()){
                    return false;
                }
                else if (new Date(this.startDate).getTime() < new Date(this.endDate).getTime()){
                    return true;
                }
                else{
                    if (parseInt(this.startTime.split(":")[0]) * 60 + parseInt(this.startTime.split(":")[1]) <  parseInt(this.endTime.split(":")[0]) * 60 + parseInt(this.endTime.split(":")[1])){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            }
        }
    }
</script>
    