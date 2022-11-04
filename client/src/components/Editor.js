import "@toast-ui/editor/dist/toastui-editor.css";

import { Editor as Writer } from "@toast-ui/react-editor";
import React, { forwardRef } from "react";

const Editor = forwardRef(({ height, onChange, type }, ref) => {
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
});

export default Editor;
