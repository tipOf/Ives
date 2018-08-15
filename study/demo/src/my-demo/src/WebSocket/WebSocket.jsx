import React, { Component } from 'react';
import './WebSocket.css';

class WebSocketWrapper extends Component {
   constructor(props) {
      super(props);

      this.socket = new WebSocket("ws://localhost:8888/ives/websocket");
      let state = {
         messages: ['初始数据！'],
         sendText: ''
      };

      this.state = state;
   }


   componentWillMount() {
      this.initWebsockt();
   }

   initWebsockt = () => {
      this.socket.onopen = this.onOpen;
      this.socket.onmessage = this.onMessage;
   }

   onOpen = () => {
      console.log('open websocket...');
   }

   onMessage = (message) => {
      console.log(message.data);
      let {messages} = this.state;
      messages.push(message.data);

      this.setState({messages});
   }

   render() {
      let {messages} = this.state;
      return (
         <div className="web-socket-wrapper">
            1 {messages.join()}
         </div>
      );
   }
}

export default WebSocketWrapper;
