<template>
        <nav class="nav nav-tabs flex-column">
            <router-link exact to="/profile-page-fishing-instructor" tag="button" class="nav-link text-start">Personal info</router-link>
            <router-link exact to="/service-crud" tag="button" class="nav-link text-start">My services</router-link>
            <router-link exact to="/reservation-history-privileged-user" tag="button" class="nav-link text-start">Reservation history</router-link>
            <router-link exact to="/service-availability" tag="button" class="nav-link text-start">My services availability</router-link>
            <router-link exact to="/" tag="button" class="nav-link text-start">New reservation</router-link>
            <router-link exact to="/" tag="button" class="nav-link text-start">Business reports</router-link>
        </nav>
</template>