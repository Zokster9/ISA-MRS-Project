<template>
    <div class="shadow-lg rounded" style="flex-shrink:0">
        <div style="width:300px;height:100%;">
            <form @submit.prevent style="padding:10px;padding-top:100px">
                <div class="form-group required">
                    <label class="control-label">Choose a service type: </label>
                    <select v-model="reservationForm.serviceType" class="form-select" aria-label="Service types">
                        <option selected value="retreat">Retreats</option>
                        <option value="ship">Ships</option>
                        <option value="adventure">Adventures</option>
                    </select>
                </div>
                <div class="form-group required">
                    <label class="control-label">Starting date </label>
                    <input v-model="reservationForm.date" :min="currentDate" id="date" type="date" class="form-control form-control-lg"/>
                </div>
                <div class="form-group required">
                    <table>
                        <tr>
                            <td>
                                <label class="control-label">Starting time </label>
                                <input v-model="reservationForm.startTime" id="time" type="time" class="form-control form-control-lg"/>
                            </td>
                            <td>
                                <label class="control-label">Ending time </label>
                                <input v-model="reservationForm.endTime" id="time" type="time" class="form-control form-control-lg"/>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="form-group required">
                    <label class="control-label">Duration of the reservation (in days) </label>
                    <input v-model="reservationForm.numberOfDays" id="name" type="number" min="1" class="form-control form-control-lg"/>
                </div>
                <div class="form-group required">
                    <label class="control-label">Number of people </label>
                    <input v-model="reservationForm.numberOfPeople" id="name" type="number" min="1" class="form-control form-control-lg"/>
                </div>
                <div class="form-group">
                    <button @click="search" :disabled="$v.reservationForm.$invalid || invalidTime || invalidDate" type="submit" class="btn btn-primary btn-lg btn-block">Search</button>
                </div>
                <div class="form-group">
                    <label>Sort by</label><br>
                    <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
                      <input @click="sort" type="radio" class="btn-check" id="btnradio1" autocomplete="off" value="price" v-model="sortBy" checked>
                      <label class="btn btn-outline-primary" for="btnradio1">Price</label>
                    
                      <input @click="sort" type="radio" class="btn-check" id="btnradio2" autocomplete="off" value="averageRating" v-model="sortBy">
                      <label class="btn btn-outline-primary" for="btnradio2">Average rating</label>
                    </div>
                </div>
            </form>
        </div>
    </div>
</template>

<script>
    import Vuelidate from 'vuelidate'
    import Vue from 'vue'
    import { required, } from 'vuelidate/lib/validators'

    Vue.use(Vuelidate)

    const isInteger = (value) => RegExp(/^[1-9]\d*$/).test(value);
    export default {
        data () {
            return {
                reservationForm: {
                    serviceType: "retreat",
                    date: null,
                    startTime: "",
                    endTime: "",
                    numberOfDays: "",
                    numberOfPeople: "",
                },
                sortBy: "price",
            }
        },
        computed: {
            currentDate() {
                let date = new Date();
                let month = date.getMonth() + 1
                if (month < 10) {
                    month = '0' + month
                }
                return date.getFullYear() + '-' + month + '-' + date.getDate()
            },
            invalidTime() {
                return this.reservationForm.startTime >= this.reservationForm.endTime
            },
            invalidDate() {
                return this.reservationForm.date < this.currentDate
            },
        },
        methods: {
            search() {
                this.$emit("search", this.reservationForm)
            },
            async sort() {
                await setTimeout(() => {
                    this.$emit("sort", this.sortBy)
                }, 100)
            }
        },
        validations: {
            reservationForm: {
                date: {
                    required,
                },
                startTime : {
                    required,
                },
                endTime : {
                    required,
                },
                numberOfDays : {
                    required,
                    isInteger,
                },
                numberOfPeople : {
                    required,
                    isInteger,
                },
            }
        }
    }
</script>