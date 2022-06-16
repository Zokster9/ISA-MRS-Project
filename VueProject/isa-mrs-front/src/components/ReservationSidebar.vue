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
                <div v-if="reservationForm.serviceType == 'retreat'" class="form-group">
                    <label class="control-label">Number of rooms </label>
                    <input v-model="reservationForm.numberOfRooms" id="name" type="number" min="1" class="form-control form-control-lg"/>
                </div>
                <div v-if="reservationForm.serviceType == 'retreat'" class="form-group">
                    <label class="control-label">Additional services </label>
                    <vue-tags-input
                    v-model="retreatTag"
                    :tags="inputRetreatTags"
                    :autocomplete-items="filteredRetreatTags"
                    :add-only-from-autocomplete="true"
                    @tags-changed="newTags => inputRetreatTags = newTags"
                    />
                </div>
                <div v-else-if="reservationForm.serviceType == 'ship'" class="form-group">
                    <label class="control-label">Additional services </label>
                    <vue-tags-input
                    v-model="shipTag"
                    :tags="inputShipTags"
                    :autocomplete-items="filteredShipTags"
                    :add-only-from-autocomplete="true"
                    @tags-changed="newTags => inputShipTags = newTags"
                    />
                </div>
                <div v-else class="form-group">
                    <label class="control-label">Additional services </label>
                    <vue-tags-input
                    v-model="adventureTag"
                    :tags="inputAdventureTags"
                    :autocomplete-items="filteredAdventureTags"
                    :add-only-from-autocomplete="true"
                    @tags-changed="newTags => inputAdventureTags = newTags"
                    />
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
    import VueTagsInput from '@johmun/vue-tags-input';

    Vue.use(Vuelidate)

    const isInteger = (value) => RegExp(/^[1-9]\d*$/).test(value);
    export default {
        props: ['retreatTags', 'shipTags', 'adventureTags'],
        components: {
            VueTagsInput,
        },
        data () {
            return {
                reservationForm: {
                    serviceType: "retreat",
                    date: null,
                    startTime: "",
                    endTime: "",
                    numberOfDays: "",
                    numberOfPeople: "",
                    numberOfRooms: "1",
                },
                sortBy: "price",
                retreatTag: '',
                shipTag: '',
                adventureTag: '',
                inputRetreatTags: [],
                inputShipTags: [],
                inputAdventureTags: [],
            }
        },
        computed: {
            currentDate() {
                let date = new Date();
                let month = date.getMonth() + 1
                if (month < 10) {
                    month = '0' + month
                }
                let day = date.getDate()
                if (day < 10) {
                    day = '0' + day
                }
                return date.getFullYear() + '-' + month + '-' + day
            },
            invalidTime() {
                if (this.reservationForm.numberOfDays === "1") {
                    return this.reservationForm.startTime >= this.reservationForm.endTime
                }
                return false;
            },
            invalidDate() {
                return this.reservationForm.date < this.currentDate
            },
            filteredRetreatTags() {
                let tags = []
                let filtered = this.retreatTags.filter(tag => {
                    return tag.name.toLowerCase().indexOf(this.retreatTag.toLowerCase()) !== -1;
                });
                for (let tag of filtered) {
                    tags.push(tag.name)
                }
                return tags;
            },
            filteredShipTags() {
                let tags = []
                let filtered = this.shipTags.filter(tag => {
                    return tag.name.toLowerCase().indexOf(this.shipTag.toLowerCase()) !== -1;
                });
                for (let tag of filtered) {
                    tags.push(tag.name)
                }
                return tags;
            },
            filteredAdventureTags() {
                let tags = []
                let filtered = this.adventureTags.filter(tag => {
                    return tag.name.toLowerCase().indexOf(this.adventureTag.toLowerCase()) !== -1;
                });
                for (let tag of filtered) {
                    tags.push(tag.name)
                }
                return tags;
            },
        },
        methods: {
            search() {
                let additionalServices = []
                if (this.reservationForm.serviceType == "retreat") {
                    for (let tag of this.inputRetreatTags) {
                        additionalServices.push(tag.text)
                    }
                }
                else if (this.reservationForm.serviceType == "ship") {
                    for (let tag of this.inputShipTags) {
                        additionalServices.push(tag.text)
                    }
                }
                else {
                    for (let tag of this.inputAdventureTags) {
                        additionalServices.push(tag.text)
                    }
                }
                this.$emit("search", this.reservationForm, additionalServices)
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
                numberOfRooms : {
                    isInteger,
                },
            }
        }
    }
</script>