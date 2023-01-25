document.addEventListener("DOMContentLoaded", function(event) {
    injectListeners()
  });

function injectListeners() {

    let addTaskButton = document.querySelector('#add-task-button')
    addTaskButton.addEventListener('click', turnOnAddTaskWindow)

    let closeAddTaskButton = document.querySelector('[data-close-button]')
    closeAddTaskButton.addEventListener('click', turnOffAddTaskWindow)

    let overlay = document.querySelector('#overlay')
    overlay.addEventListener('click', turnOffAddTaskWindow)

}

function turnOnAddTaskWindow()
{
    document.querySelector('#overlay').classList.add('active')
    document.querySelector('.task-window').classList.add('active')
}

function turnOffAddTaskWindow()
{
    document.querySelector('#overlay').classList.remove('active')
    document.querySelector('.task-window').classList.remove('active')
}
