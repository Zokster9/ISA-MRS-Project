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
                alert(this.startDate)
                alert(this.startTime)
                alert(this.endDate)
                alert(this.endTime)
                alert(id);
            },
        },
        computed: {
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
    