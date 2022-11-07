import "@toast-ui/editor/dist/toastui-editor.css";

import { Editor as Writer, Viewer } from "@toast-ui/react-editor";
import React, { forwardRef } from "react";
import '@toast-ui/editor/dist/toastui-editor-viewer.css';

const Editor = forwardRef(({ height, onChange, type,initialValue}, ref) => {
  if (type === "write")
    return (
      <Writer
        initialValue="내용을 입력하세요."
        previewStyle="tab"
        height={height}
        initialEditType="markdown"
        useCommandShortcut={true}
        onChange={onChange}
        ref={ref}
      />  
    );
    return <Viewer ref={ref} initialValue={initialValue} />;
});


export default Editor;

