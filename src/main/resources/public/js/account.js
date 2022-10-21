const usernameCard = document.getElementById("username-card")
const passwordCard = document.getElementById("password-card")
const openUsernameCard = document.getElementById("open-username-card")
const openPasswordCard = document.getElementById("open-password-card")
const closeUsernameCard = document.getElementById("close-username-card")
const closePasswordCard = document.getElementById("close-password-card")

openUsernameCard.addEventListener("click", () => {
    usernameCard.style.display = "block"
})

openPasswordCard.addEventListener("click", () => {
    passwordCard.style.display = "block"
})

closeUsernameCard.addEventListener("click", () => {
    usernameCard.style.display = "none"
})

closePasswordCard.addEventListener("click", () => {
    passwordCard.style.display = "none"
})

