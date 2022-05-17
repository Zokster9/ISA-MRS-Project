<template>
    <div style="width: 100%; height: 100%;">
        <NavbarClient></NavbarClient>
        <div class="d-flex">
            <ReservationSidebar @search="search" @sort="sort" style="background-color: #ffffff;"></ReservationSidebar>
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
                additionalServices: [],
                reservationForm: null,
            }
        },
        computed: {
            orderedResults() {
                return _orderBy(this.availableReservations, this.sortBy)
            },
        },
        methods: {
            search(reservationForm) {
                this.additionalServices = []
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
                },
                {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                })
                .then(() => {
                    alert("Reservation is successful. Check your email.")
                    this.modal = false
                })
                .catch(() => {
                    alert("Something went wrong!")
                })
            }
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