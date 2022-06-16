<template>
    <div style="width: 100%; height: 100%;">
        <NavbarClient></NavbarClient>
        <div class="d-flex">
            <ReservationSidebar :adventureTags="adventureTags" :retreatTags="retreatTags" :shipTags="shipTags" @search="search" @sort="sort" style="background-color: #ffffff;"></ReservationSidebar>
            <div style="width:50%;height:100%;margin:auto;margin-top:110px;">
                <div v-if="availableReservations" class="vertical-center">
                    <div v-if="orderedResults.length != 0">
                        <SearchReservationResult @reserve="reserve" v-for="reservationResult in orderedResults" :searchResult="reservationResult" :serviceType="type" :key="reservationResult.id"></SearchReservationResult>
                    </div>
                    <div v-else>
                        <h1>There are no services available for reservation with your desired parameters.</h1>
                    </div>
                </div>
                <div v-else class="mx-auto" style="margin-top: 50px;">
                    <h1>Here you can see available services for reservations when you enter your desired parameters.</h1>
                </div>
            </div>
        </div>
        <div v-if="modal">
          <transition name="model">
              <div class="modal-mask">
                  <div class="modal-wrapper">
                      <div class="modal-dialog">
                          <div class="modal-content">
                              <div class="modal-header">
                                  <button class="btn btn-primary my-3" @click="quitReservation">Cancel</button>
                                  <h4 class="modal-title">Reservation confirmation</h4>
                              </div>
                              <div class="modal-body">
                                  <h5>Choose additional services</h5>
                                  <form class="w-50 mx-auto mt-5">
                                      <div class="form-group mb-3">
                                        <label>Additional services:</label>
                                        <br>
                                        <template v-for="tag in selectedService.additionalServices">
                                            <div class="form-group mb-3" :key="tag">
                                                <input type="checkbox" v-model="additionalServices" :value="tag" :key="tag"/>
                                                <label :for="tag.key"> {{tag}}</label>
                                            </div>
                                        </template>
                                    </div>
                                    <button class="btn btn-primary my-3" @click="confirmReservation">Reserve</button>
                                  </form>
                              </div>
                          </div>
                      </div>
                  </div>
              </div>
          </transition>
      </div>
    </div>
</template>

<script>
    import _orderBy from 'lodash/orderBy';
    import NavbarClient from '@/components/NavbarClient.vue'
    import ReservationSidebar from '@/components/ReservationSidebar.vue'
    import SearchReservationResult from '@/components/SearchReservationResult.vue'
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
import router from '@/router';

    Vue.use(VueAxios, axios)

    export default {
        name: 'ClientReservation',
        components: {
            NavbarClient,
            ReservationSidebar,
            SearchReservationResult,
        },
        data () {
            return {
                availableReservations: null,
                sortBy: 'price',
                type: "",
                modal: false,
                selectedService: null,
                retreatTags: [],
                shipTags: [],
                adventureTags: [],
                reservationForm: null,
            }
        },
        computed: {
            orderedResults() {
                return _orderBy(this.availableReservations, this.sortBy)
            },
        },
        methods: {
            search(reservationForm, additionalServices) {
                console.log(additionalServices)
                this.type = reservationForm.serviceType;
                this.reservationForm = reservationForm;
                axios.get("http://localhost:8088/reservations/" + this.type + "/getAvailableReservations", {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    },
                    params: {
                        serviceType: reservationForm.serviceType,
                        fromDate: reservationForm.date,
                        fromTime: reservationForm.startTime,
                        toTime: reservationForm.endTime,
                        numOfDays: reservationForm.numberOfDays,
                        numOfPeople: reservationForm.numberOfPeople,
                        additionalServices: additionalServices,
                        numOfRooms: reservationForm.numberOfRooms,
                    }
                })
                .then(response => {
                    this.availableReservations = response.data;
                })
                .catch(() => {
                    alert("Something went wrong!")
                })
            },
            sort(sort) {
                this.sortBy = sort;
                console.log(this.sortBy)
            },
            reserve(serviceForReservation) {
                this.modal = true;
                this.selectedService = serviceForReservation;
            },
            quitReservation() {
                this.modal = false;
            },
            confirmReservation() {
                let date = new Date(this.reservationForm.date)
                let toDate = date.setDate(date.getDate() + parseInt(this.reservationForm.numberOfDays) - 1)
                toDate = new Date(toDate)
                axios.post("http://localhost:8088/reservations/makeAReservation", {
                    fromDate: this.reservationForm.date,
                    toDate: toDate,
                    fromTime: this.reservationForm.startTime,
                    toTime: this.reservationForm.endTime,
                    price: this.reservationForm.numberOfDays * this.selectedService.price,
                    serviceId: this.selectedService.id,
                    numOfPeople: this.reservationForm.numberOfPeople,
                    serviceType: this.reservationForm.serviceType,
                },
                {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                })
                .then(() => {
                    alert("Reservation is successful. Check your email.")
                    this.modal = false
                    this.availableReservations = this.availableReservations.filter(service => service.id != this.selectedService.id);
                })
                .catch(() => {
                    alert("Something went wrong!")
                })
            }
        },
        mounted() {
            if (window.sessionStorage.getItem("role") != "ROLE_client") {
                router.push("/");
            }
            axios.get("http://localhost:8088/clients/getLoggedClient", {
                    headers:{
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                }).then((response) => {
                    let client = response.data
                    if (client.penalized === true) {
                        alert("You cannot make reservations, you have been PENALIZED! Check your penalty points screen for more information.")
                        router.push("/penalty-points");
                    }
                })
            axios.get("http://localhost:8088/tags/retreat", {
                    headers:{
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                })
            .then((response) => {
                this.retreatTags = response.data
            })
            axios.get("http://localhost:8088/tags/ship", {
                    headers:{
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                })
            .then((response) => {
                this.shipTags = response.data
            })
            axios.get("http://localhost:8088/tags/adventure", {
                    headers:{
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                })
            .then((response) => {
                this.adventureTags = response.data
            })
        }
    }
</script>

<style>
    .modal-mask {
        position: fixed;
        z-index: 9998;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, .5);
        transition: opacity .3s ease;
    }

    .modal-wrapper {
        vertical-align: middle;
    }
</style>