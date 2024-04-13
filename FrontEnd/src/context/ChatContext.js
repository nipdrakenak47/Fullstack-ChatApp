
import { createContext,useReducer} from "react";

export const ChatContext = createContext();

export const ChatContextProvider = ({children}) => {

    const INTIAL_STATE = {
        chatId : null,
        user : {}
    }

    const chatReducer = (state,action) => {
        switch(action.type){
            case 'CHANGE_USER' :
                return {
                    currentUser : action.payload.currentUser,
                    currentUserImageUrl : action.payload.currentUserImageUrl,
                    friendName : action.payload.userFriendName,
                    friendImageUrl : action.payload.imageUrl,
                    chatId : action.payload.currentUser + ' ' + action.payload.userFriendName
                }
            case 'lgout_user' :
                return {
                    chatId : null,
                    user : {}
                }
            default :
                return state;
        }
    }

    const [state,dispatch] = useReducer(chatReducer,INTIAL_STATE);

    return (
    <ChatContext.Provider value = {{ data:state,dispatch}}>
        {children}
    </ChatContext.Provider>
    );
}